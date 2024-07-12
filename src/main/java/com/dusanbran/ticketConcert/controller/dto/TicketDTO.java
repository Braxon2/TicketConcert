package com.dusanbran.ticketConcert.controller.dto;

import java.time.LocalDateTime;

public record TicketDTO(
        long id,
        LocalDateTime purchaseTime,
        TicketTypeDTO type,
        UserDTO user
) {
}
