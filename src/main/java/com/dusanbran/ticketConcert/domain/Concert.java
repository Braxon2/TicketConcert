package com.dusanbran.ticketConcert.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Concert {

    @Id
    @GeneratedValue
    private long id;

    private String address;

    private String venueName;

    private String description;

    private int capacity;


    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(
            name = "musician_id"
    )
    @JsonBackReference
    private Musician musician;

    @OneToMany(
            mappedBy = "concert"
    )
    @JsonIgnore
    private List<TicketType> ticketTypeList;

    public Concert() {
    }

    public Concert(String address, String venueName, String description, int capacity, LocalDateTime dateTime) {
        this.address = address;
        this.venueName = venueName;
        this.description = description;
        this.capacity = capacity;
        this.dateTime = dateTime.withSecond(0).withNano(0);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime.withSecond(0).withNano(0);
    }

    public Musician getMusician() {
        return musician;
    }

    public void setMusician(Musician musician) {
        this.musician = musician;
    }

    public List<TicketType> getTicketTypeList() {
        return ticketTypeList;
    }

    public void setTicketTypeList(List<TicketType> ticketTypeList) {
        this.ticketTypeList = ticketTypeList;
    }
}
