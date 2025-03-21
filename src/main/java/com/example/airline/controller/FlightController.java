package com.example.airline.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.airline.model.Flight;
import com.example.airline.service.FlightService;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    // ✅ Get all flights, sorted by departure time
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights(@RequestParam(required = false, defaultValue = "desc") String sort) {
        List<Flight> flights = flightService.getAllFlights(sort);
        return ResponseEntity.ok(flights);
    }

    // ✅ Get flight by ID
    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        Optional<Flight> flight = flightService.getFlightById(id);
        return flight.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // ✅ Get flights by departure date
    @GetMapping("/date")
    public ResponseEntity<List<Flight>> getFlightsByDate(@RequestParam String date) {
        try {
            LocalDate departureDate = LocalDate.parse(date);
            List<Flight> flights = flightService.getFlightsByDate(departureDate);

            if (flights.isEmpty()) {
                return ResponseEntity.noContent().build(); // ✅ Return 204 instead of 404
            }
            return ResponseEntity.ok(flights);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null); // ✅ Handles invalid date format
        }
    }
}
