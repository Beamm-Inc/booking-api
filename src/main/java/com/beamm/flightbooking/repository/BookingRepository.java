package com.beamm.flightbooking.repository;

import com.beamm.flightbooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {
    Booking findByBookingReference(String confirmationCode);
}
