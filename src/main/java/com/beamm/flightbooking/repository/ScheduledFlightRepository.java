package com.beamm.flightbooking.repository;

import com.beamm.flightbooking.model.ScheduledFlight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduledFlightRepository extends JpaRepository<ScheduledFlight, Integer> {
}
