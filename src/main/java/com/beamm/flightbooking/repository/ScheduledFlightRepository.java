package com.beamm.flightbooking.repository;

import com.beamm.flightbooking.model.Airport;
import com.beamm.flightbooking.model.ScheduledFlight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduledFlightRepository extends JpaRepository<ScheduledFlight, Integer> {
    @Query("delete from ScheduledFlight u where u.flight.flightNumber = ?1")
    ScheduledFlight removeScheduledFlightByFlightNumber(String flightNumber);
    @Query("select u from ScheduledFlight u where u.flight.flightNumber = ?1")
    ScheduledFlight getScheduledFlightByFlightNumber(String flightNumber);
    @Query("select u from ScheduledFlight u where u.flight.destination = ?1 and u.flight.origin =?1 and u.departureDate=?1")
    List<ScheduledFlight> searchScheduledFlightOneWay(String depatureCity, String arrivalCity, LocalDate departureDate);
//    @Query("select u from ScheduledFlight u where u.flight.destination = ? 1 and u.flight.origin =?1 and u.departureDate=?1")
//    List<ScheduledFlight> searchScheduledFlightRoundTrip(String depatureCity, String arrivalCity, LocalDate departureDate, LocalDate returnDate);

    Page<ScheduledFlight> findByFlightOriginAndFlightDestination(Airport depart, Airport arrival, Pageable pageable);
}
