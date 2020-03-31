package com.beamm.flightbooking.service.impl;

import com.beamm.flightbooking.model.Airplane;
import com.beamm.flightbooking.repository.AirplaneRepository;
import com.beamm.flightbooking.service.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirplaneServiceImpl implements AirplaneService
{
    @Autowired
    AirplaneRepository airplaneRepository;

    @Override
    public Page<Airplane> getAllAirplanesPaged(int pageNo) {
        return airplaneRepository.findAll(PageRequest.of(pageNo,20));
    }

    @Override
    public List<Airplane> getAllAirplanesList() {
        return airplaneRepository.findAll();
    }

    @Override
    public Airplane getAirplaneById(Integer id) {
        return airplaneRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteAirplaneById(Integer id) {
        airplaneRepository.deleteById(id);
    }

    @Override
    public Airplane saveAirplane(Airplane airplane) {
        return airplaneRepository.save(airplane);
    }
}
