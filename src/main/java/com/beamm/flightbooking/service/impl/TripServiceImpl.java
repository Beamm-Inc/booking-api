package com.beamm.flightbooking.service.impl;

import com.beamm.flightbooking.model.Booking;
import com.beamm.flightbooking.model.Passenger;
import com.beamm.flightbooking.model.Trip;
import com.beamm.flightbooking.repository.TripRepository;
import com.beamm.flightbooking.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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

    @Override
    @Modifying
    @Transactional
    public void deleteByTicketNumber(String ticketNumber)  throws Exception {
        Optional<Trip> trip = Optional.ofNullable(tripRepository.findByTicketNumber(ticketNumber));

        if (trip.isPresent()) {
            List<Trip> trips = tripRepository.findAll();
            Iterator<Trip> iterator = trips.iterator();
            Trip trip2;

            while (iterator.hasNext()) {
                trip2 = iterator.next();

                if (trip2.getTicketNumber().equals(ticketNumber)) {
                    iterator.remove();

                }
            }
        }
            else{
                throw new Exception("No employee record exist for given id");
            }
        }
    }
