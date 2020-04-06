package com.beamm.flightbooking.repository;

import com.beamm.flightbooking.model.FlightManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightManagerRepository extends JpaRepository<FlightManager, Integer> {
    FlightManager findByUserNameAndPassword(String userName, String password);
}
