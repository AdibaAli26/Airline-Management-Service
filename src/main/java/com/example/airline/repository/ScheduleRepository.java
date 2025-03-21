package com.example.airline.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.airline.model.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<Schedule> findByFlightIdAndDate(Long flightId, LocalDate date);
}
