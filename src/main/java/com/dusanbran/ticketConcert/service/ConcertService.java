package com.dusanbran.ticketConcert.service;

import com.dusanbran.ticketConcert.controller.dto.ConcertDTO;
import com.dusanbran.ticketConcert.controller.dto.TicketDTO;
import com.dusanbran.ticketConcert.controller.dto.TicketTypeDTO;
import com.dusanbran.ticketConcert.controller.mapper.ConcertMapper;
import com.dusanbran.ticketConcert.controller.mapper.MusicianMapper;
import com.dusanbran.ticketConcert.controller.mapper.TicketMapper;
import com.dusanbran.ticketConcert.controller.mapper.TicketTypeMapper;
import com.dusanbran.ticketConcert.domain.*;
import com.dusanbran.ticketConcert.exceptions.ConcertExistException;
import com.dusanbran.ticketConcert.exceptions.NoSuchElementFoundExceptions;
import com.dusanbran.ticketConcert.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConcertService {

    private final ConcertRepository concertRepository;

    private final MusicianRepository musicianRepository;

    private final TicketTypeRepository ticketTypeRepository;

    private final UserRepository userRepository;

    private final TicketRepository ticketRepository;

    private final BuyingTicketRepository buyingTicketRepository;

    private final ConcertMapper concertMapper;

    private final TicketTypeMapper ticketTypeMapper;

    private final TicketMapper ticketMapper;


    public ConcertService(ConcertRepository concertRepository, MusicianRepository musicianRepository, TicketTypeRepository ticketTypeRepository, UserRepository userRepository, TicketRepository ticketRepository, BuyingTicketRepository buyingTicketRepository, ConcertMapper concertMapper, TicketTypeMapper ticketTypeMapper, TicketMapper ticketMapper, MusicianMapper musicianMapper) {
        this.concertRepository = concertRepository;
        this.musicianRepository = musicianRepository;
        this.ticketTypeRepository = ticketTypeRepository;
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
        this.buyingTicketRepository = buyingTicketRepository;
        this.concertMapper = concertMapper;
        this.ticketTypeMapper = ticketTypeMapper;
        this.ticketMapper = ticketMapper;
    }

    public ConcertDTO create(Concert newConcert) throws Exception {
        Optional<Musician> optionalMusician = musicianRepository.findById(newConcert.getMusician().getId());

        Musician musician = musicianRepository.findById((long) newConcert.getMusician().getId()).orElseThrow(() -> new NoSuchElementFoundExceptions("Musician not found with ID: " + newConcert.getMusician().getId()));

//        if(optionalMusician.isEmpty()){
//            throw new Exception("This musician does not exist!!!");
//        }
        newConcert.setMusician(musician);
//
//        newConcert.setMusician(optionalMusician.get());
//        Concert concert = concertRepository.findByDateTime(newConcert.getDateTime()).orElseThrow(()-> new ConcertExistException("This concert already exist"));
        Optional<Concert> optionalConcert = concertRepository.findByDateTime(newConcert.getDateTime());
        if(optionalConcert.isEmpty()) {
            concertRepository.save(newConcert);
            return concertMapper.toConcertDTO(newConcert);

        }else throw new ConcertExistException("You can't have two concerts with same date and time!");

    }

    public List<ConcertDTO> getAll() {
        return concertRepository.findAll()
                .stream()
                .map(concertMapper::toConcertDTO)
                .collect(Collectors.toList());
    }

    public TicketTypeDTO addTicket(TicketType ticketType, int concertId) throws Exception {

        Optional<Concert> optionalConcert = concertRepository.findById((long) concertId);

        if(optionalConcert.isEmpty()){
            throw new Exception("This concert does not exist!");
        }

        Optional<TicketType> optionalTicketType = ticketTypeRepository.findById((long) ticketType.getId());

        if(optionalTicketType.isPresent()){
            throw new Exception("This ticket type already exist!");
        }

        var concert = optionalConcert.get();

        var capacity = concert.getCapacity();

        int sum = 0;
        for (int i = 0; i < concert.getTicketTypeList().size(); i++) {
            if(ticketType.getType().equals(concert.getTicketTypeList().get(i).getType())){
                throw new Exception("This ticket type already exist for this concert!");
            }
            sum += concert.getTicketTypeList().get(i).getTotalTickets();
        }

        if(ticketType.getTotalTickets() > capacity || ticketType.getTotalTickets() > capacity-sum){
            throw new Exception("The quantity of this type of ticket is over the limit of this concert");
        }

        concert.getTicketTypeList().add(ticketType);
        ticketType.setConcert(concert);

        concertRepository.save(concert);
        ticketTypeRepository.save(ticketType);
        return ticketTypeMapper.toTicketTypeDTO(ticketType);


    }

    public List<TicketTypeDTO> getAllTicketTypes(int concertId) throws Exception {
        Optional<Concert> optionalConcert = concertRepository.findById((long) concertId);

        if(optionalConcert.isEmpty()){
            throw new Exception("This concert does not exist!");
        }

        return optionalConcert.get().getTicketTypeList()
                .stream()
                .map(ticketTypeMapper::toTicketTypeDTO)
                .collect(Collectors.toList());

    }

    public List<ConcertDTO> getAllActive() {
        return concertRepository.findByDateTimeAfter(LocalDateTime.now())
                .stream()
                .map(concertMapper::toConcertDTO)
                .collect(Collectors.toList());
    }

    public List<TicketDTO> purchase(List<BuyingTicket> buyingTickets, int concertId, int userId) throws Exception {

        Optional<Concert> optionalConcert = concertRepository.findById((long) concertId);

        List<Ticket> tickets = new ArrayList<>();

        if(optionalConcert.isEmpty()){
            throw new Exception("This concert does not exist!");
        }

        Optional<User> optionalUser = userRepository.findById((long) userId);

        if(optionalUser.isEmpty()){
            throw new Exception("This user does not exist!");
        }

        LocalDateTime currentTime = LocalDateTime.now();

        for (int i = 0; i < buyingTickets.size(); i++) {

            Optional<TicketType> optionalTicketType = ticketTypeRepository.findById(buyingTickets.get(i).getTicketType().getId());

            if(optionalTicketType.isEmpty()){
                throw new Exception("This ticket type does not exist for this concert!");
            }

            int availableTickets = ticketTypeRepository.findById(buyingTickets.get(i).getTicketType().getId()).get().getTotalTickets()
                    -
                    ticketTypeRepository.findById(buyingTickets.get(i).getTicketType().getId()).get().getSoldTickets();


            if(buyingTickets.get(i).getQuantity() < 1 || buyingTickets.get(i).getQuantity() > availableTickets){
                throw new Exception("Quantity of " +buyingTickets.get(i).getTicketType().getType()  + " ticket is over the limit");
            }

            optionalTicketType.get().setSoldTickets(optionalTicketType.get().getSoldTickets() + buyingTickets.get(i).getQuantity());

            /*
                1. Azuriras broj prodatih karata u TicketType-u
                2.Svuda azuriras prodate karte
                3. Pravljenje pojedinacne karte
                4a. Nakon napravljene karte dodati u listu ticketType-a
                4b. CUvanje sve na kraju
                5. Vrati Kartu(Ticket)
             */


            //pravljenje karte
            for (int j = 0; j < buyingTickets.get(i).getQuantity(); j++) {
                Ticket ticketItem = new Ticket(currentTime);
                ticketItem.setTicketType(optionalTicketType.get());
                ticketItem.setUser(optionalUser.get());

                tickets.add(ticketItem);

                //dodavanje karte u listu vlasnika te vrste karte
                optionalTicketType.get().getTickets().add(ticketItem);

                //Dodavanje user-u kartu
                optionalUser.get().getTickets().add(ticketItem);

                ticketRepository.save(ticketItem);
            }




            buyingTicketRepository.save(buyingTickets.get(i));
            userRepository.save(optionalUser.get());

            ticketTypeRepository.save(optionalTicketType.get());

        }

        return tickets
                .stream()
                .map(ticketMapper::toTicketDTO)
                .collect(Collectors.toList());
    }
}
