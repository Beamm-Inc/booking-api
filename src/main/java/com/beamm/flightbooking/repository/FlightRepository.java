package com.beamm.flightbooking.repository;

import com.beamm.flightbooking.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Integer>
{

//    Page<Flight> findByDepartureDateAndOrigin_AirportCityContainingAndDestination_AirportCityContaining(LocalDate date, String origin, String destination, Pageable pageable);

    Page<Flight> findByOrigin_AirportCityContainingAndDestination_AirportCityContaining(String origin,String destination,Pageable pageable);




}
