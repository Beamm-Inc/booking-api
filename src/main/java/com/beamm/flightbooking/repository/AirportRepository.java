package com.beamm.flightbooking.repository;

import com.beamm.flightbooking.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport,Integer>
{
}
