package com.dusanbran.ticketConcert.controller.mapper;

import com.dusanbran.ticketConcert.controller.dto.ConcertDTO;
import com.dusanbran.ticketConcert.domain.Concert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConcertMapper {

    @Autowired
    private MusicianMapper musicianMapper;



   /* public Concert toConcert(ConcertDTO dto){

    }*/

    public ConcertDTO toConcertDTO(Concert concert){
        return new ConcertDTO(concert.getAddress(),
                concert.getVenueName(),
                concert.getDescription(),
                concert.getCapacity(),
                concert.getDateTime(),
                musicianMapper.toMusicianDTO(concert.getMusician()));
    }

}
