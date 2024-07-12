package com.dusanbran.ticketConcert.service;

import com.dusanbran.ticketConcert.repository.TicketTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class TicketTypeService {

    private final TicketTypeRepository ticketTypeRepository;

    public TicketTypeService(TicketTypeRepository ticketTypeRepository) {
        this.ticketTypeRepository = ticketTypeRepository;
    }
}
