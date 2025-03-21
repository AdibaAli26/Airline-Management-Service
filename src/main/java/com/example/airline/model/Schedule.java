package com.example.airline.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "schedules") // ✅ Matches table name in data.sql
public class Schedule {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "available_seats", nullable = false) // ✅ Ensures proper column name
    private int availableSeats;

    @Column(name = "date", nullable = false) // ✅ Matches column in database
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false) // ✅ Foreign key reference
    private Flight flight;

    // ✅ No-argument constructor (JPA requires it)
    public Schedule() {}

    // ✅ Parameterized constructor
    public Schedule(int availableSeats, LocalDate date, Flight flight) {
        this.availableSeats = availableSeats;
        this.date = date;
        this.flight = flight;
    }

    // ✅ Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", availableSeats=" + availableSeats +
                ", date=" + date +
                ", flightId=" + (flight != null ? flight.getId() : "null") + // ✅ Avoid recursion
                '}';
    }
}
