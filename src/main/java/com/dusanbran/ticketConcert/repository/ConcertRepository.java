package com.dusanbran.ticketConcert.repository;

import com.dusanbran.ticketConcert.domain.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, Long> {
    List<Concert> findByDateTimeAfter(LocalDateTime now);

    Optional<Concert> findByDateTime(LocalDateTime dateTime);
}
