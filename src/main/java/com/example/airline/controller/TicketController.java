package com.example.airline.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.airline.model.Ticket;
import com.example.airline.service.TicketService;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    // ✅ Book a ticket
    @PostMapping
    public ResponseEntity<Ticket> bookTicket(@RequestBody Map<String, Object> request) {
        Long flightId = ((Number) request.get("flightId")).longValue();
        Ticket ticket = new Ticket();
        ticket.setPassengerName((String) request.get("passengerName"));
        ticket.setSeatNumber((String) request.get("seatNumber"));

        return ResponseEntity.ok(ticketService.bookTicket(flightId, ticket));
    }

    // ✅ Retrieve all tickets
    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    // ✅ Retrieve ticket details by ID
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
        Optional<Ticket> ticket = ticketService.getTicketById(id);
        return ticket.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // ✅ Cancel a ticket
    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelTicket(@PathVariable Long id) {
        boolean isDeleted = ticketService.cancelTicket(id);
        if (isDeleted) {
            return ResponseEntity.ok("Ticket canceled successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
