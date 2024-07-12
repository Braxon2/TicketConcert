package com.dusanbran.ticketConcert.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TicketTypeTest {

    private TicketType ticketType;

    @BeforeEach
    void setUp() {
        ticketType = new TicketType();
    }

    @AfterEach
    void tearDown() {
        ticketType = null;
    }
    @Test
    void setId() {
        ticketType.setId(1L);
        assertEquals(1L, ticketType.getId());
    }

    @Test
    void setType() {
        ticketType.setType("fan-pit");
        assertEquals("fan-pit", ticketType.getType());
    }

    @Test
    void setPrice() {
        ticketType.setPrice(1.0);
        assertEquals(1.0, ticketType.getPrice());
    }

    @Test
    void setTotalTickets() {
        ticketType.setTotalTickets(10);
        assertEquals(10, ticketType.getTotalTickets());
    }

    @Test
    void setSoldTickets() {
        ticketType.setSoldTickets(10);
        assertEquals(10, ticketType.getSoldTickets());
    }

    @Test
    void setConcert() {
        Concert concert = new Concert();
        concert.setId(1L);
        ticketType.setConcert(concert);
        assertEquals(concert, ticketType.getConcert());
    }

    @Test
    void setTickets() {
        List<Ticket> tickets = new ArrayList<>();
        Ticket t = new Ticket();
        t.setId(1L);
        tickets.add(t);
        ticketType.setTickets(tickets);
        assertEquals(tickets, ticketType.getTickets());
    }
}