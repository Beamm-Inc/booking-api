package com.beamm.flightbooking.controller;

import com.beamm.flightbooking.model.FlightClass;
import com.beamm.flightbooking.model.Trip;
import com.beamm.flightbooking.model.Trip;
import com.beamm.flightbooking.service.AddressService;
import com.beamm.flightbooking.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(TripController.BASE_URL)
public class TripController {
    @Autowired
    TripService tripService;

    public static final String BASE_URL = "/api/v1/trip";

    @GetMapping("/{id}")
    public Trip getTripById(@PathVariable Integer id) {
        return tripService.getTripById(id);
    }


    @PostMapping
    public ResponseEntity<Trip> saveNewTrip(@RequestBody @Valid Trip trip,Model model,BindingResult result, Principal principal) {
             //trip.setFlightClass(FlightClass.BUSINESS);
        return new ResponseEntity<>(this.tripService.saveTrip(trip), HttpStatus.OK);
    }


    @GetMapping("/ticket")
    public Trip getByTicketNumber(@PathVariable String ticket) {
        return tripService.getByTicketNumber(ticket);
    }

    @GetMapping
    public List<Trip> getAllTrips() {
        return tripService.getAllTripList();
    }
}
