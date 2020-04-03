package com.beamm.flightbooking.repository;

import com.beamm.flightbooking.model.FlightManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightManagerRepository extends JpaRepository<FlightManager, Integer> {
}
