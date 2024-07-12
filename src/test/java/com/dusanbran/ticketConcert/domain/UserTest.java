package com.dusanbran.ticketConcert.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @AfterEach
    void tearDown() {
        user = null;
    }

    @Test
    void setId() {
        user.setId(1L);
        assertEquals(1L, user.getId());
    }

    @Test
    void setFirstName() {
        user.setFirstName("John");
        assertEquals("John", user.getFirstName());
    }

    @Test
    void setLastName() {
        user.setLastName("Doe");
        assertEquals("Doe", user.getLastName());
    }

    @Test
    void setUsername() {
        user.setUsername("John");
        assertEquals("John", user.getUsername());
    }

    @Test
    void setPassword() {
        user.setPassword("john");
        assertEquals("john", user.getPassword());
    }

    @Test
    void setTickets() {
        List<Ticket> tickets = new ArrayList<Ticket>();
        tickets.add(new Ticket(LocalDateTime.now()));
        user.setTickets(tickets);
        assertEquals(tickets, user.getTickets());
    }
}