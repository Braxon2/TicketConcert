package com.dusanbran.ticketConcert.controller;

import com.dusanbran.ticketConcert.controller.dto.ConcertDTO;
import com.dusanbran.ticketConcert.controller.dto.TicketDTO;
import com.dusanbran.ticketConcert.controller.dto.TicketTypeDTO;
import com.dusanbran.ticketConcert.domain.BuyingTicket;
import com.dusanbran.ticketConcert.domain.Concert;
import com.dusanbran.ticketConcert.domain.TicketType;
import com.dusanbran.ticketConcert.service.ConcertService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/concerts")
public class ConcertController {

    private final ConcertService concertService;

    public ConcertController(ConcertService concertService) {
        this.concertService = concertService;
    }

    @PostMapping
    public ConcertDTO createConcert(@RequestBody Concert newConcert) throws Exception {
        return concertService.create(newConcert);
    }

    @GetMapping
    public List<ConcertDTO> getAll(){
        return concertService.getAll();
    }

    @PostMapping("/{concert_id}/addTicketType")
    public TicketTypeDTO addTicket(@RequestBody TicketType ticketType, @PathVariable int concert_id) throws Exception {
        return concertService.addTicket(ticketType,concert_id);
    }

    @GetMapping("/{concert_id}/ticketTypes")
    public List<TicketTypeDTO> getAllTicketTypes(@PathVariable int concert_id) throws Exception {
        return concertService.getAllTicketTypes(concert_id);
    }

    @GetMapping("/allActive")
    public List<ConcertDTO> allActiveConcerts(){
        return concertService.getAllActive();
    }

    @PostMapping("/{concert_id}/is/{user_id}/purchasing")
    public List<TicketDTO> purchasingTickets(@RequestBody List<BuyingTicket> buyingTickets, @PathVariable int concert_id, @PathVariable int user_id) throws Exception {
        return concertService.purchase(buyingTickets, concert_id, user_id);
    }

}
