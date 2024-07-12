package com.dusanbran.ticketConcert.repository;

import com.dusanbran.ticketConcert.domain.Musician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicianRepository extends JpaRepository<Musician, Long> {
}
