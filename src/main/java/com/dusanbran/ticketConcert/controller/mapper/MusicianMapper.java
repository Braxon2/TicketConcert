package com.dusanbran.ticketConcert.controller.mapper;


import com.dusanbran.ticketConcert.controller.dto.MusicianDTO;
import com.dusanbran.ticketConcert.domain.Musician;
import org.springframework.stereotype.Component;

@Component
public class MusicianMapper {

    public Musician toMusician(MusicianDTO dto){
        return new Musician(dto.musicianName(), dto.biography());
    }

    public MusicianDTO toMusicianDTO(Musician musician){
        return new MusicianDTO(musician.getId(),musician.getMusicianName(),musician.getBiography());
    }

}
