package com.dusanbran.ticketConcert.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Ticket {

    @Id
    @GeneratedValue
    private long id;

    private LocalDateTime purchaseTime;

    @ManyToOne
    @JoinColumn(
            name="tickets"
    )
    private TicketType ticketType;

    @ManyToOne
    @JoinColumn(
            name = "user_id"
    )
    private User user;

    public Ticket() {
    }

    public Ticket(LocalDateTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(LocalDateTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
