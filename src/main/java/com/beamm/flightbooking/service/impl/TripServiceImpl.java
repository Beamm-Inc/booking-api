package com.beamm.flightbooking.service.impl;

import com.beamm.flightbooking.model.Trip;
import com.beamm.flightbooking.repository.TripRepository;
import com.beamm.flightbooking.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    TripRepository tripRepository;
    @Override
    public Trip getTripById(Integer id) {
        return tripRepository.findById(id).orElse(null);
    }

    @Override
    public Trip saveTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    @Override
    public Trip getByTicketNumber(String ticketNumber) {
        return tripRepository.findByTicketNumber(ticketNumber);
    }

    @Override
    public List<Trip> getAllTripList() {
        return tripRepository.findAll();
    }
}
