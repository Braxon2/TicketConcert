package com.dusanbran.ticketConcert.service;

import com.dusanbran.ticketConcert.controller.dto.ConcertDTO;
import com.dusanbran.ticketConcert.controller.dto.MusicianDTO;
import com.dusanbran.ticketConcert.controller.mapper.ConcertMapper;
import com.dusanbran.ticketConcert.controller.mapper.MusicianMapper;
import com.dusanbran.ticketConcert.domain.Musician;
import com.dusanbran.ticketConcert.repository.MusicianRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MusicianService {

    private final MusicianRepository musicianRepository;

    private final MusicianMapper musicianMapper;

    private final ConcertMapper concertMapper;

    public MusicianService(MusicianRepository musicianRepository, MusicianMapper musicianMapper, ConcertMapper concertMapper) {
        this.musicianRepository = musicianRepository;
        this.musicianMapper = musicianMapper;
        this.concertMapper = concertMapper;
    }

    public MusicianDTO create(Musician musician) {
       return musicianMapper.toMusicianDTO( musicianRepository.save(musician));
    }

    public List<MusicianDTO> getAll() {
        return musicianRepository.findAll()
                .stream()
                .map(musicianMapper::toMusicianDTO)
                .collect(Collectors.toList());
    }

    public List<ConcertDTO> findActiveConcerts(int musicianId) throws Exception {

        Optional<Musician> optionalMusician = musicianRepository.findById((long) musicianId);

        if(optionalMusician.isEmpty()){
            throw new Exception("This musician does not exist!");
        }

        return optionalMusician.get().getConcerts()
                .stream().
                map(concertMapper::toConcertDTO).
                filter(concert -> concert.dateTime().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
    }
}
