package com.beamm.flightbooking.service.impl;

import com.beamm.flightbooking.model.ScheduledFlight;
import com.beamm.flightbooking.repository.ScheduledFlightRepository;
import com.beamm.flightbooking.service.ScheduledFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduledFlightServiceImpl implements ScheduledFlightService {
    @Autowired
    ScheduledFlightRepository scheduledFlightRepository;

    @Override
    public Page<ScheduledFlight> getAllScheduledFlightPages(int pageNo) {
        return null;
    }

    @Override
    public List<ScheduledFlight> getAllScheduledFlight() {
        return scheduledFlightRepository.findAll();
    }

    @Override
    public ScheduledFlight getScheduledFlightById(Integer id) {
        return scheduledFlightRepository.findById(id).orElse(null);
    }

    @Override
    public ScheduledFlight getScheduledFlightByFlightNumber(String flightNumber) {
        return null;
    }

    @Override
    public HttpStatus deleteScheduledFlightById(Integer id) {
        return null;
    }

    @Override
    public ScheduledFlight saveScheduledFlight(ScheduledFlight scheduledFlight) {
        return null;
    }
}
