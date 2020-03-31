package com.beamm.flightbooking.service.impl;


import com.beamm.flightbooking.model.Airport;
import com.beamm.flightbooking.repository.AirportRepository;
import com.beamm.flightbooking.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportServiceImpl implements AirportService
{

    @Autowired
    AirportRepository airportRepository;

    @Override
    public Page<Airport> getAllAirportsPaged(int pageNo) {
        return airportRepository.findAll(PageRequest.of(pageNo,20));
    }

    @Override
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    @Override
    public Airport getAirportById(Integer id) {
        return airportRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteAirportById(Integer id) {
        airportRepository.deleteById(id);
    }

    @Override
    public Airport saveAirport(Airport airport) {
        return airportRepository.save(airport);
    }
}
