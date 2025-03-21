package com.example.airline.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.airline.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findAllByOrderByDepartureTimeAsc();
    List<Flight> findAllByOrderByDepartureTimeDesc();
    
    // ✅ Find flights by departure date
    List<Flight> findByDepartureDate(LocalDate departureDate);
}
