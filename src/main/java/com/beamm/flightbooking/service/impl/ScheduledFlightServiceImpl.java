package com.beamm.flightbooking.service.impl;

import com.beamm.flightbooking.model.Airport;
import com.beamm.flightbooking.model.Passenger;
import com.beamm.flightbooking.model.ScheduledFlight;
import com.beamm.flightbooking.repository.PassengerRepository;
import com.beamm.flightbooking.repository.ScheduledFlightRepository;
import com.beamm.flightbooking.service.ScheduledFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ScheduledFlightServiceImpl implements ScheduledFlightService {
    @Autowired
    ScheduledFlightRepository scheduledFlightRepository;
    @Autowired
    PassengerRepository passengerRepository;

    @Override
    public Page<ScheduledFlight> getAllScheduledFlightPages(int pageNo) {
        return scheduledFlightRepository.findAll(PageRequest.of(pageNo,20));
    }

    @Override
    public List<ScheduledFlight> getAllScheduledFlights() {
        return scheduledFlightRepository.findAll();
    }

    @Override
    public ScheduledFlight getScheduledFlightById(Integer id) {
        return scheduledFlightRepository.findById(id).orElse(null);
    }

    @Override
    public ScheduledFlight getScheduledFlightByFlightNumber(String flightNumber) {
        return scheduledFlightRepository.getScheduledFlightByFlightNumber(flightNumber);
    }

    @Override
    public ResponseEntity<ScheduledFlight> removeScheduledFlightById(Integer id) {
        ScheduledFlight scheduledFlight = scheduledFlightRepository.findById(id).orElse(null);

        if(scheduledFlight != null) {
            scheduledFlightRepository.deleteById(id);
            return new ResponseEntity<ScheduledFlight>(scheduledFlight, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<ScheduledFlight>(scheduledFlight, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ScheduledFlight> removeScheduledFlightByFlightNumber(String flightNumber) {
        ScheduledFlight scheduledFlight = scheduledFlightRepository.removeScheduledFlightByFlightNumber(flightNumber);

        if(scheduledFlight == null)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<ScheduledFlight>(scheduledFlightRepository.removeScheduledFlightByFlightNumber(flightNumber), HttpStatus.OK);
        }
    }

    @Override
    public ScheduledFlight addScheduledFlight(ScheduledFlight scheduledFlight) {
       Iterator<Passenger> iterator = scheduledFlight.getPassengers().iterator();
       while(iterator.hasNext()){
           passengerRepository.save(iterator.next());
       }

        return scheduledFlightRepository.save(scheduledFlight);
    }

    @Override
    public Page<ScheduledFlight> searchScheduledFlightOneWay(int departId, int arrivalId, LocalDate departDate, int pageNo) {
        return scheduledFlightRepository.findByFlightOriginAirportIDAndFlightDestinationAirportIDAndDepartureDate(departId, arrivalId, departDate, PageRequest.of(pageNo, 20));
    }
}
