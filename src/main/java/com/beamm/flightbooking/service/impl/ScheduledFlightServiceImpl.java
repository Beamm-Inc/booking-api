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
    public HttpStatus removeScheduledFlightById(Integer id) {
        try{
            scheduledFlightRepository.deleteById(id);
            return HttpStatus.valueOf("Flight successfully deleted.");
        }
        catch(Exception  ex){
            return HttpStatus.FORBIDDEN;
        }
    }

    @Override
    public HttpStatus removeScheduledFlightByFlightNumber(String flightNumber) {
        ScheduledFlight scheduledFlight = scheduledFlightRepository.removeScheduledFlightByFlightNumber(flightNumber);

        if(scheduledFlight == null)
        {
            return HttpStatus.valueOf("Sleleted the scheduled flight successfully.");
        }
        else {
            return HttpStatus.valueOf("No such flight record found.");
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

//    @Override
//    public List<ScheduledFlight> searchScheduledFlightOneWay(String depatureCity, String arrivalCity, LocalDate departureDate) {
//        return scheduledFlightRepository.searchScheduledFlightOneWay(depatureCity, arrivalCity, departureDate);
//    }
//
//    @Override
//    public List<List<ScheduledFlight>> searchScheduledFlightRoundTrip(String depatureCity, String arrivalCity, LocalDate departureDate, LocalDate returnDate) {
//        List<List<ScheduledFlight>> returnFlights = new ArrayList<>();
//
//        // Search departure flights
//        List<ScheduledFlight> departureFlights = new ArrayList<>();
//        departureFlights = scheduledFlightRepository.searchScheduledFlightOneWay(depatureCity, arrivalCity, departureDate);
//        returnFlights.add(departureFlights);
//
//        // Search arrival flights
//        List<ScheduledFlight> arrivalFlights = new ArrayList<>();
//        arrivalFlights = scheduledFlightRepository.searchScheduledFlightOneWay(arrivalCity, depatureCity, returnDate);
//        returnFlights.add(arrivalFlights);
//
//        return returnFlights;
//    }

//    @Override
//    public Page<ScheduledFlight> findAllByFlightOriginAndFlightDestination(Airport depart, Airport arrival, int pageNo) {
//        return scheduledFlightRepository.findAllByFlightOriginAndFlightDestination(depart, arrival,PageRequest.of(pageNo,20));
//    }
//
//    @Override
//    public Page<ScheduledFlight> findAllByFlightOrigin_FlightIDAndFlightDestination_FlightID(int depart, int arrival, int pageNo) {
//        return scheduledFlightRepository.findAllByFlightOrigin_FlightIDAndFlightDestination_FlightID(depart, arrival,PageRequest.of(pageNo,20));
//    }
//
//    @Override
//    public Page<ScheduledFlight> searchScheduledFlightOneWay(String depatureCity, String arrivalCity, LocalDate departureDate, int pageNo) {
//        return null;
//    }
//
//    @Override
//    public Page<ScheduledFlight> searchScheduledFlightRoundTrip(String depatureCity, String arrivalCity, LocalDate departureDate, LocalDate returnDate, int pageNo) {
//        return null;
//    }
//
//    @Override
//    public Page<ScheduledFlight> findAllByFlightOriginAndFlightDestinationAndDepartureDate(Airport depart, Airport arrival, LocalDate departDate, int pageNo) {
//        return scheduledFlightRepository.findAllByFlightOriginAndFlightDestinationAndDepartureDate(depart, arrival, departDate, PageRequest.of(pageNo, 20));
//    }

    @Override
    public Page<ScheduledFlight> searchScheduledFlightOneWay(Airport depart, Airport arrival, LocalDate departDate, int pageNo) {
        return scheduledFlightRepository.findAllByFlightOriginAndFlightDestinationAndDepartureDate(depart, arrival, departDate, PageRequest.of(pageNo, 20));
    }
}
