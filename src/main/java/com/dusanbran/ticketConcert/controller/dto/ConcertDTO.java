package com.dusanbran.ticketConcert.controller.dto;

import java.time.LocalDateTime;


public record ConcertDTO(
        String address,
        String venueName,
        String description,
        int capacity,
        LocalDateTime dateTime,
        MusicianDTO musicianDto
) {


}
