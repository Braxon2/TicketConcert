package com.dusanbran.ticketConcert.controller;

import com.dusanbran.ticketConcert.controller.dto.ConcertDTO;
import com.dusanbran.ticketConcert.controller.dto.MusicianDTO;
import com.dusanbran.ticketConcert.domain.Musician;
import com.dusanbran.ticketConcert.service.MusicianService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/musicians")
public class MusicianController {

    private final MusicianService musicianService;


    public MusicianController(MusicianService musicianService) {
        this.musicianService = musicianService;
    }


    @PostMapping
    public MusicianDTO createMusician(@RequestBody Musician musician) {
        return musicianService.create(musician);
    }

    @GetMapping
    public List<MusicianDTO> getAll(){
        return musicianService.getAll();
    }

    @GetMapping("/{musician_id}/activeConcerts")
    public List<ConcertDTO> findActiveConcerts(@PathVariable int musician_id) throws Exception {
        return musicianService.findActiveConcerts(musician_id);
    }

}
