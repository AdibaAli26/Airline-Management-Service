package com.example.airline.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.airline.exception.FlightNotFoundException;
import com.example.airline.exception.SeatAlreadyBookedException;
import com.example.airline.model.Flight;
import com.example.airline.model.Ticket;
import com.example.airline.repository.FlightRepository;
import com.example.airline.repository.TicketRepository;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private FlightRepository flightRepository;

    // ✅ Book a ticket
    public Ticket bookTicket(Long flightId, Ticket ticket) {
        // Check if flight exists
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with ID: " + flightId));

        // Check if seat is already booked
        if (ticketRepository.existsByFlightIdAndSeatNumber(flightId, ticket.getSeatNumber())) {
            throw new SeatAlreadyBookedException("Seat " + ticket.getSeatNumber() + " is already booked for flight ID: " + flightId);
        }

        // Set flight and booking date before saving
        ticket.setFlight(flight);
        ticket.setBookingDate(LocalDate.now());

        return ticketRepository.save(ticket);
    }

    // ✅ Retrieve all tickets
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    // ✅ Retrieve ticket by ID
    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    // ✅ Cancel a ticket
    public boolean cancelTicket(Long id) {
        if (ticketRepository.existsById(id)) {  // Check if ticket exists
            ticketRepository.deleteById(id);
            return true;  // Successfully deleted
        }
        return false;  // Ticket not found
    }
}
