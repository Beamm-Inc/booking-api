package com.beamm.flightbooking.service;

import com.beamm.flightbooking.model.Airport;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AirportService
{
    Page<Airport> getAllAirportsPaged(int pageNo);
    List<Airport> getAllAirports();
    Airport getAirportById(Integer id);
    void deleteAirportById(Integer id);
    Airport saveAirport(Airport airport);
}
