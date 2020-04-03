package com.beamm.flightbooking.service;

import com.beamm.flightbooking.model.ScheduledFlight;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.List;

public interface ScheduledFlightService {
    List<ScheduledFlight> getAllScheduledFlights();
    ScheduledFlight getScheduledFlightById(Integer id);
    Page<ScheduledFlight> getAllScheduledFlightPages(int pageNo);
    ScheduledFlight getScheduledFlightByFlightNumber(String flightNumber);
    HttpStatus removeScheduledFlightById(Integer id);
    HttpStatus removeScheduledFlightByFlightNumber(String flightNumber);
    ScheduledFlight addScheduledFlight(ScheduledFlight scheduledFlight);
    List<ScheduledFlight> searchScheduledFlightOneWay(String depatureCity, String arrivalCity, LocalDate departureDate);
    List<List<ScheduledFlight>> searchScheduledFlightRoundTrip(String depatureCity, String arrivalCity, LocalDate departureDate, LocalDate returnDate);
}
