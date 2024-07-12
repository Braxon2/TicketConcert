package com.dusanbran.ticketConcert.controller.mapper;

import com.dusanbran.ticketConcert.controller.dto.TicketDTO;
import com.dusanbran.ticketConcert.domain.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    @Autowired
    private TicketTypeMapper ticketTypeMapper;

    @Autowired
    private UserMapper userMapper;

    public TicketDTO toTicketDTO(Ticket ticket) {
        return new TicketDTO(ticket.getId(),
                ticket.getPurchaseTime(),
                ticketTypeMapper.toTicketTypeDTO(ticket.getTicketType()),
                userMapper.toDto(ticket.getUser()));
    }

}
