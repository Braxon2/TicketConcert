package com.dusanbran.ticketConcert.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class TicketType {

    @Id
    @GeneratedValue
    private long id;

    private String type;

    private double price;

    private int totalTickets;

    private int soldTickets;

    @ManyToOne
    @JoinColumn(
            name = "concert_id"
    )
    private Concert concert;

    @OneToMany(
            mappedBy = "ticketType"
    )
    @JsonIgnore
    private List<Ticket> tickets;

    public TicketType() {
    }

    public TicketType(String type, double price, int totalTickets, int soldTickets) {
        this.type = type;
        this.price = price;
        this.totalTickets = totalTickets;
        this.soldTickets = soldTickets;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getSoldTickets() {
        return soldTickets;
    }

    public void setSoldTickets(int soldTickets) {
        this.soldTickets = soldTickets;
    }

    public Concert getConcert() {
        return concert;
    }

    public void setConcert(Concert concert) {
        this.concert = concert;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketType that = (TicketType) o;
        return id == that.id && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }
}
