package com.beamm.flightbooking.repository;

import com.beamm.flightbooking.model.Booking;
import com.beamm.flightbooking.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {
    //@Query
    Booking findByBookingReference(String confirmationCode);
    //Passenger addPassenger(Passenger passenger);
}
