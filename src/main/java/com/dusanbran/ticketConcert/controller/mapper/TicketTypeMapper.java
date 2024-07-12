package com.dusanbran.ticketConcert.controller.mapper;

import com.dusanbran.ticketConcert.controller.dto.TicketTypeDTO;
import com.dusanbran.ticketConcert.domain.TicketType;
import org.springframework.stereotype.Component;

@Component
public class TicketTypeMapper {

    public TicketTypeDTO toTicketTypeDTO(TicketType ticketType){
        return new TicketTypeDTO(ticketType.getType(),ticketType.getPrice());
    }



}
