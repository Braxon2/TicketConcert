package com.dusanbran.ticketConcert.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {

    private Ticket ticket;

    @BeforeEach
    void setUp() {
        ticket = new Ticket();
    }

    @AfterEach
    void tearDown() {
        ticket = null;
    }

    @Test
    void setId() {
        ticket.setId(1L);
        assertEquals(1L, ticket.getId());
    }

    @Test
    void setPurchaseTime() {
        LocalDateTime time = LocalDateTime.now();
        ticket.setPurchaseTime(time);
        assertEquals(time, ticket.getPurchaseTime());
    }

    @Test
    void setTicketType() {
        TicketType ticketType = new TicketType();
        ticketType.setId(1L);
        ticket.setTicketType(ticketType);
        assertEquals(ticketType, ticket.getTicketType());
    }

    @Test
    void setUser() {
        User user = new User();
        user.setId(1L);
        ticket.setUser(user);
        assertEquals(user, ticket.getUser());
    }
}