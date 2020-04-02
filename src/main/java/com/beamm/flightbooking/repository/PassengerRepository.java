package com.beamm.flightbooking.repository;

import com.beamm.flightbooking.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
}
