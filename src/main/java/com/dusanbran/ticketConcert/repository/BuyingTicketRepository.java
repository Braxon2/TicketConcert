package com.dusanbran.ticketConcert.repository;

import com.dusanbran.ticketConcert.domain.BuyingTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyingTicketRepository extends JpaRepository<BuyingTicket, Long> {
}
