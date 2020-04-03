package com.beamm.flightbooking.service;

import com.beamm.flightbooking.model.Airplane;
import com.beamm.flightbooking.model.Trip;

import java.util.List;

public interface TripService {
    Trip getTripById(Integer id);
    // update
    Trip saveTrip(Trip trip);
    Trip getByTicketNumber(String  ticketNumber);
    List<Trip> getAllTripList();
    void deleteByTicketNumber(String ticketNumber) throws Exception;
}
