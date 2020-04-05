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
    HttpStatus removeScheduledFlightById(Integer id);
    HttpStatus removeScheduledFlightByFlightNumber(String flightNumber);
    ScheduledFlight addScheduledFlight(ScheduledFlight scheduledFlight);
////    List<ScheduledFlight> searchScheduledFlightOneWay(String depatureCity, String arrivalCity, LocalDate departureDate);
////    List<List<ScheduledFlight>> searchScheduledFlightRoundTrip(String depatureCity, String arrivalCity, LocalDate departureDate, LocalDate returnDate);
//    Page<ScheduledFlight> findAllByFlightOriginAndFlightDestination(Airport depart, Airport arrival, int pageNo);
//    Page<ScheduledFlight> findAllByFlightOrigin_FlightIDAndFlightDestination_FlightID(int depart, int arrival, int pageNo);
//    Page<ScheduledFlight> searchScheduledFlightOneWay(String depatureCity, String arrivalCity, LocalDate departureDate, int pageNo);
//    Page<ScheduledFlight> searchScheduledFlightRoundTrip(String depatureCity, String arrivalCity, LocalDate departureDate, LocalDate returnDate, int pageNo);
//    Page<ScheduledFlight> findAllByFlightOriginAndFlightDestinationAndDepartureDate(Airport depart, Airport arrival, LocalDate departDate, int pageNo);
    Page<ScheduledFlight> searchScheduledFlightOneWay(Airport depart, Airport arrival, LocalDate departDate, int pageNo);
}
