package com.beamm.flightbooking.service;

import com.beamm.flightbooking.model.Flight;
import org.springframework.data.domain.Page;

import java.time.LocalDate;


public interface FlightService
{
    public abstract Page<Flight> getAllFlightsPaged(int pageNo);
    public abstract Page<Flight> getSearchedFlightsPaged(LocalDate date, String origin, String destination, int pageNo);

    public abstract Flight getFlightById(Integer id);
    public abstract void deleteFlightById(Integer id);
    public abstract Flight saveFlight(Flight flight);

}
