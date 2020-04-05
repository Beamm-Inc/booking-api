package com.beamm.flightbooking.service;

import com.beamm.flightbooking.model.FlightManager;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface FlightManagerService {
    Page<FlightManager> getAllFlightManagerPages(int pageNo);
    List<FlightManager> getAllFlightManagers();
    FlightManager getFlightManagerById(Integer id);
//    FlightManager getFlightManagerByUserNameAndPassword(String userName, String password);
    HttpStatus deleteFlightManagerById(Integer id);
    FlightManager saveFlightManager(FlightManager flightManager);
}
