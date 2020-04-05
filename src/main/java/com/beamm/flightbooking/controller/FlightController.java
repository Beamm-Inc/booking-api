package com.beamm.flightbooking.controller;

import com.beamm.flightbooking.dto.Flightdto;
import com.beamm.flightbooking.model.Airport;
import com.beamm.flightbooking.model.Flight;
import com.beamm.flightbooking.model.Flight;
import com.beamm.flightbooking.service.AirplaneService;
import com.beamm.flightbooking.service.AirportService;
import com.beamm.flightbooking.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping(FlightController.BASE_URL)
public class FlightController {
    public static final String BASE_URL = "/api/v1/flight";

    @Autowired
    FlightService flightService;

    @Autowired
    AirplaneService airplaneService;

    @Autowired
    AirportService airportService;

    @GetMapping
    Page<Flight> list(@RequestParam(defaultValue = "0") int pageNo) {
        return flightService.getAllFlightsPaged(pageNo);
    }

    @PostMapping(value = {"/search"})
    public Page<Flight> searchFlight(@ModelAttribute("flightdto") Flightdto flightdto,
                                     Model model, @RequestParam(defaultValue = "0") int pageNo) {
        return flightService.getSearchedFlightsPaged(flightdto.getDateOfDeparture(), flightdto.getFrom(), flightdto.getTo(), pageNo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Flight> addNewFlight(@RequestBody @Valid Flight flight, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return null;

        }
        return new ResponseEntity<Flight>(this.flightService.saveFlight(flight), HttpStatus.OK);
    }

    @PostMapping(value = {"/edit"})
    public ResponseEntity<Flight> saveNewPerson(@RequestBody @Valid Flight flight, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return null;

        }
        return new ResponseEntity<>(this.flightService.saveFlight(flight), HttpStatus.OK);
    }
   /* public Flight UpdateFlight(@Valid @ModelAttribute("flight") Flight flight,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return null;
        }
        flight = flightService.saveFlight(flight);
        return flight;
    }*/

    @GetMapping(value = {"delete/{flightId}"})
    public String deleteBook(@PathVariable Integer flightId) {
        flightService.deleteFlightById(flightId);
        return "True";
    }

}
