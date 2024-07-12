package com.dusanbran.ticketConcert.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MusicianTest {

    private Musician musician;

    @BeforeEach
    void setUp() {
        musician = new Musician();
    }

    @AfterEach
    void tearDown() {
        musician = null;
    }

    @Test
    void setId() {
        musician.setId(1L);
        assertEquals(musician.getId(), 1L);
    }

    @Test
    void setMusicianName() {
        musician.setMusicianName("test");
        assertEquals("test", musician.getMusicianName());
    }

    @Test
    void setBiography() {
        musician.setBiography("test");
        assertEquals("test", musician.getBiography());
    }

    @Test
    void setConcerts() {
        List<Concert> concerts = new ArrayList<>();
        Concert concert = new Concert();
        concert.setId(1L);
        concerts.add(concert);
        musician.setConcerts(concerts);
        assertEquals(concerts, musician.getConcerts());

    }
}