package com.beamm.flightbooking.service;


import com.beamm.flightbooking.model.Airplane;
import org.springframework.data.domain.Page;

import java.util.List;


public interface AirplaneService
{
    Page<Airplane> getAllAirplanesPaged(int pageNo);
    List<Airplane> getAllAirplanesList();
    Airplane getAirplaneById(Integer id);
    void deleteAirplaneById(Integer id);
    Airplane saveAirplane(Airplane airplane);
}
