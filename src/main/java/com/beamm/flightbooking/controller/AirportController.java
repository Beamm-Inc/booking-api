package com.beamm.flightbooking.controller;

import com.beamm.flightbooking.model.Airport;
import com.beamm.flightbooking.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(AirportController.BASE_URL)
public class AirportController
{
    public static final String BASE_URL = "/api/v1/airport";

    @Autowired
    AirportService airportService;

    @GetMapping
    public Page<Airport> list(@RequestParam(defaultValue = "0") int pageNo)
    {
        return airportService.getAllAirportsPaged(pageNo);
    }

    @PostMapping
    public Airport addNewAirportF(@Valid @ModelAttribute("airport") Airport airport){
        return airportService.saveAirport(airport);
    }

    @PostMapping(value = {"/edit"})
    public Airport UpdateAirportF(@Valid @ModelAttribute("airport") Airport airport,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return null;
        }
        return airportService.saveAirport(airport);
    }

    @GetMapping(value = {"/delete/{airportId}"})
    public void deleteAirportF(@PathVariable Integer airportId) {
        airportService.deleteAirportById(airportId);
    }

}
