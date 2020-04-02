package com.beamm.flightbooking.controller;

import com.beamm.flightbooking.model.Passenger;
import com.beamm.flightbooking.model.Person;
import com.beamm.flightbooking.service.PassengerService;
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

        return passengerService.getAllPassenger();
    }
    @GetMapping
    public Page<Passenger> list(@RequestParam(defaultValue = "0") int pageNo)
    {
        return passengerService.getAllPassengerPages(pageNo);
    }
    @PostMapping
    public ResponseEntity<Passenger> saveNewCustomer(@RequestBody @Valid Passenger passenger, BindingResult result, Principal principal)
    {
        return new ResponseEntity<Passenger>(this.passengerService.savePassenger(passenger), HttpStatus.OK);
    }
}
