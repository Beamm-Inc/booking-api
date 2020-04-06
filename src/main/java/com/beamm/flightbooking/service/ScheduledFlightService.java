package com.beamm.flightbooking.service;

import com.beamm.flightbooking.model.Airport;
import com.beamm.flightbooking.model.ScheduledFlight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface ScheduledFlightService {
    List<ScheduledFlight> getAllScheduledFlights();
    Page<ScheduledFlight> getAllScheduledFlightPages(int pageNo);
    ScheduledFlight getScheduledFlightById(Integer id);
    ScheduledFlight getScheduledFlightByFlightNumber(String flightNumber);
    ResponseEntity<ScheduledFlight> removeScheduledFlightById(Integer id);
    ResponseEntity<ScheduledFlight> removeScheduledFlightByFlightNumber(String flightNumber);
    ScheduledFlight addScheduledFlight(ScheduledFlight scheduledFlight);

    Page<ScheduledFlight> searchScheduledFlightOneWay(int depart, int arrival, LocalDate departDate, int pageNo);
}
