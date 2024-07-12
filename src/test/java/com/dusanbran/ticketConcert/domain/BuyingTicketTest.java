package com.dusanbran.ticketConcert.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuyingTicketTest {

    private BuyingTicket buyingTicket;

    @BeforeEach
    void setUp() {
        buyingTicket = new BuyingTicket();
    }

    @AfterEach
    void tearDown() {
        buyingTicket = null;
    }

    @Test
    void setId() {
        buyingTicket.setId(1L);
        assertEquals(1L, buyingTicket.getId());
    }

    @Test
    void setQuantity() {
        buyingTicket.setQuantity(3);
        assertEquals(3, buyingTicket.getQuantity());
    }

    @Test
    void setUser() {
        User user = new User();
        user.setId(1L);
        buyingTicket.setUser(user);
        assertEquals(user, buyingTicket.getUser());
    }

    @Test
    void setTicketType() {
        TicketType ticketType = new TicketType();
        ticketType.setId(1L);
        buyingTicket.setTicketType(ticketType);
        assertEquals(ticketType, buyingTicket.getTicketType());
    }
}