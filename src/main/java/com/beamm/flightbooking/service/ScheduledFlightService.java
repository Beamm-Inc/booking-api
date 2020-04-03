package com.beamm.flightbooking.service;

import com.beamm.flightbooking.model.ScheduledFlight;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface ScheduledFlightService {
    Page<ScheduledFlight> getAllScheduledFlightPages(int pageNo);
    List<ScheduledFlight> getAllScheduledFlight();
    ScheduledFlight getScheduledFlightById(Integer id);
    ScheduledFlight getScheduledFlightByFlightNumber(String flightNumber);
    HttpStatus deleteScheduledFlightById(Integer id);
    ScheduledFlight saveScheduledFlight(ScheduledFlight scheduledFlight);
//    ScheduledFlight getScheduledFlightByDepartureAndArrival(String depatureCity,String arrivalCity);
}
