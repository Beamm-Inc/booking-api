package com.beamm.flightbooking.controller;

import com.beamm.flightbooking.model.Airplane;
import com.beamm.flightbooking.model.Passenger;
import com.beamm.flightbooking.model.Person;
import com.beamm.flightbooking.model.Trip;
import com.beamm.flightbooking.repository.TripRepository;
import com.beamm.flightbooking.service.PassengerService;
import com.beamm.flightbooking.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(PassengerController.BASE_URL)
public class PassengerController {
    public static final String BASE_URL = "/api/v1/passenger";

    @Autowired
    PassengerService passengerService;
    @Autowired
    TripService tripService;
    @GetMapping("/{id}")
    public Passenger getPassengerById(@PathVariable Integer id) {

        return passengerService.getPassengerById(id);
    }

    @GetMapping("/{passport/{passport}}")
    public Passenger getPassengerByPassport(@PathVariable String passport) {

        return passengerService.getPassengerByPassport(passport);
    }

    @GetMapping("/all")
    public List<Passenger> getAllPassengers() {

        return passengerService.getAllPassengers();
    }

    @GetMapping
    public Page<Passenger> list(@RequestParam(defaultValue = "0") int pageNo)
    {
        return passengerService.getAllPassengerPages(pageNo);
    }
    Trip trip = new Trip();
    @PostMapping
    public ResponseEntity<Passenger> saveNewCustomer(@RequestBody @Valid Passenger passenger, BindingResult result, Principal principal)
    {              trip.setTicketNumber(tripService.randomTicketNumGen(12));
        return new ResponseEntity<Passenger>(this.passengerService.savePassenger(passenger), HttpStatus.OK);
    }
}
