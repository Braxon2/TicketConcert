package com.dusanbran.ticketConcert.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConcertTest {

    private Concert concert;

    @BeforeEach
    void setUp() {
        concert = new Concert();
    }

    @AfterEach
    void tearDown() {
        concert = null;
    }

    @Test
    void setId() {
        concert.setId(1L);
        assertEquals(1L, concert.getId());
    }

    @Test
    void setAddress() {
        concert.setAddress("address");
        assertEquals("address", concert.getAddress());
    }

    @Test
    void setVenueName() {
        concert.setVenueName("venueName");
        assertEquals("venueName", concert.getVenueName());
    }

    @Test
    void setDescription() {
        concert.setDescription("description");
        assertEquals("description", concert.getDescription());
    }

    @Test
    void setCapacity() {
        concert.setCapacity(10);
        assertEquals(10, concert.getCapacity());
    }

    @Test
    void setDateTime() {
        LocalDateTime time = LocalDateTime.now().withSecond(0).withNano(0);
        concert.setDateTime(time);
        assertEquals(time, concert.getDateTime());
    }

    @Test
    void setMusician() {
        Musician musician = new Musician();
        musician.setId(1L);
        concert.setMusician(musician);
        assertEquals(musician, concert.getMusician());
    }

    @Test
    void setTicketTypeList() {
        List<TicketType> list = new ArrayList<>();
        TicketType t1 = new TicketType();
        TicketType t2 = new TicketType();
        t1.setId(1L);
        t2.setId(2L);
        list.add(t1);
        list.add(t2);
        concert.setTicketTypeList(list);
        assertEquals(list, concert.getTicketTypeList());
    }
}