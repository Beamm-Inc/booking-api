package com.beamm.flightbooking.repository;

import com.beamm.flightbooking.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip,Integer> {
    Trip findByTicketNumber(String ticket);
    Trip deleteByTicketNumber(String ticketNumber);

}
