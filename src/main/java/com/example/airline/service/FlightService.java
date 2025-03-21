package com.example.airline.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.airline.model.Flight;
import com.example.airline.repository.FlightRepository;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> getAllFlights(String sort) {
        if ("asc".equalsIgnoreCase(sort)) {
            return flightRepository.findAllByOrderByDepartureTimeAsc();
        } else {
            return flightRepository.findAllByOrderByDepartureTimeDesc();
        }
    }

    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    public List<Flight> getFlightsByDate(LocalDate date) {
        List<Flight> flights = flightRepository.findByDepartureDate(date);
        System.out.println("Flights found for date " + date + ": " + flights); // ✅ Debugging to check if data is retrieved
        return flights;
    }
}
