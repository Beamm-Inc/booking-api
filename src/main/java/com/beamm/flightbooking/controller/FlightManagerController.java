package com.beamm.flightbooking.controller;

import com.beamm.flightbooking.model.*;
import com.beamm.flightbooking.service.*;
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
@RequestMapping(FlightManagerController.BASE_URL)
public class FlightManagerController {
    @Autowired
    FlightManagerService flightManagerService;

    @Autowired
    ScheduledFlightService scheduledFlightService;

    @Autowired
    FlightService flightService;

    @Autowired
    AirportService  airportService;

    @Autowired
    AirplaneService airplaneService;

    public static final String BASE_URL = "/api/v1/flightmanager";

    @GetMapping("/{id}")
    public FlightManager getFlightManagerById(@PathVariable Integer id) {

        return flightManagerService.getFlightManagerById(id);
    }

    @GetMapping(value="/username/{username}/{password}")
    public FlightManager getAdminByUserNameAndPassword(@PathVariable String username, String password) {

        return flightManagerService.getFlightManagerByUserNameAndPassword(username, password);
    }

//    @GetMapping("/login")
//    public int loginuservalidation(@PathVariable ("user") String username,
//                                   @PathVariable ("password") String password){
//
//        return profileInterface.loginuservalidate(username, password);
//    }

    @GetMapping("/all")
    public List<FlightManager> getAllFlightManagers() {

        return flightManagerService.getAllFlightManagers();
    }

    @GetMapping
    public Page<FlightManager> list(@RequestParam(defaultValue = "0") int pageNo)
    {
        return flightManagerService.getAllFlightManagerPages(pageNo);
    }

    // Scheduled Flight Handling

    @GetMapping("/get/scheduledflight/{id}")
    public ResponseEntity<ScheduledFlight> getScheduledFlightById(@PathVariable Integer id)    {
        ScheduledFlight flight = scheduledFlightService.getScheduledFlightById(id);

        if(flight == null)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ScheduledFlight>(scheduledFlightService.getScheduledFlightById(id), HttpStatus.OK);
    }

    @PostMapping("/add/scheduledflight")
    public ResponseEntity<ScheduledFlight> addNewScheduledFlight(@RequestBody @Valid ScheduledFlight scheduledFlight, BindingResult result, Principal principal)
    {
        System.out.println("----->>>>>>>>");
        return new ResponseEntity<ScheduledFlight>(scheduledFlightService.addScheduledFlight(scheduledFlight), HttpStatus.OK);
    }

    @PostMapping("/update/scheduledflight/{id}")
    public ResponseEntity<ScheduledFlight> updateScheduledFlight(@PathVariable Integer id, @RequestBody @Valid ScheduledFlight scheduledFlight, BindingResult result, Principal principal)
    {
        ScheduledFlight flight = scheduledFlightService.getScheduledFlightById(id);

        if(flight == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        scheduledFlight.setScheduledflightID(id);
        return new ResponseEntity<ScheduledFlight>(scheduledFlightService.addScheduledFlight(scheduledFlight), HttpStatus.OK);
    }

    @PostMapping ("/remove/scheduledflight/{id}")
    public ResponseEntity<ScheduledFlight> removeScheduledFlight(@PathVariable Integer id)
    {
        ScheduledFlight flight = scheduledFlightService.getScheduledFlightById(id);

        if(flight == null)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        scheduledFlightService.removeScheduledFlightById(id);
        return new ResponseEntity<ScheduledFlight>(flight, HttpStatus.OK);
    }

    // Flight Handling

    @GetMapping("/get/flight/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Integer id)   {
        return new ResponseEntity<Flight>(flightService.getFlightById(id), HttpStatus.OK);
    }

    @PostMapping("/add/flight")
    public ResponseEntity<Flight> addNewFlight(@RequestBody @Valid Flight flight, BindingResult result, Principal principal)  {
        return new ResponseEntity<Flight>(flightService.saveFlight(flight), HttpStatus.OK);
    }

    @PostMapping("/update/flight/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Integer id, @RequestBody @Valid Flight flight, BindingResult result, Principal principal)  {
        Flight oldFlight = flightService.getFlightById(id);

        if(oldFlight == null)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        flight.setFlightID(id);
        return new ResponseEntity<Flight>(flightService.saveFlight(flight), HttpStatus.OK);
    }

    @PostMapping("/remove/flight/{id}")
    public ResponseEntity<Flight> removeFlightbyId(@PathVariable Integer id)    {
        Flight flight = flightService.getFlightById(id);

        if(flight == null)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        flightService.deleteFlightById(id);
        return new ResponseEntity<Flight>(flight, HttpStatus.OK);
    }

    // Airport Handling

    @GetMapping("/get/airport/{id}")
    public ResponseEntity<Airport> getAirport(@PathVariable Integer id) {
        return new ResponseEntity<Airport>(airportService.getAirportById(id), HttpStatus.OK);
    }

    @PostMapping("/add/airport")
    public ResponseEntity<Airport> addNewAirport(@RequestBody @Valid Airport airport, BindingResult result, Principal principal)  {
        return new ResponseEntity<Airport>(airportService.saveAirport(airport), HttpStatus.OK);
    }

    @PostMapping("/update/airport/{id}")
    public ResponseEntity<Airport> updateFlight(@PathVariable Integer id, @RequestBody @Valid Airport airport, BindingResult result, Principal principal)  {
        Airport oldAirport = airportService.getAirportById(id);

        if(oldAirport == null)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        airport.setAirportID(id);
        return new ResponseEntity<Airport>(airportService.saveAirport(airport), HttpStatus.OK);
    }

    @PostMapping("/remove/airport/{id}")
    public ResponseEntity<Airport> removeAirportbyId(@PathVariable Integer id)    {
        Airport airport = airportService.getAirportById(id);

        if(airport == null)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

       airportService.deleteAirportById(id);
        return new ResponseEntity<Airport>(airport, HttpStatus.OK);
    }

    // Airplane Handling

    @GetMapping("/get/airplane/{id}")
    public ResponseEntity<Airplane> getAirplane(@PathVariable Integer id) {
        return new ResponseEntity<Airplane>(airplaneService.getAirplaneById(id), HttpStatus.OK);
    }

    @PostMapping("/add/airplane")
    public ResponseEntity<Airplane> addNewAirplane(@RequestBody @Valid Airplane airplane, BindingResult result, Principal principal)    {
        return new ResponseEntity<Airplane>(airplaneService.saveAirplane(airplane), HttpStatus.OK);
    }

    @PostMapping("/update/airplane/{id}")
    public ResponseEntity<Airplane> updateAirplane(@PathVariable Integer id, @RequestBody @Valid Airplane airplane, BindingResult result, Principal principal)    {
        Airplane oldAirplane = airplaneService.getAirplaneById(id);

        if(oldAirplane == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        airplane.setAirplaneID(id);
        return new ResponseEntity<Airplane>(airplaneService.saveAirplane(airplane), HttpStatus.OK);
    }

    @PostMapping("/remove/airplane/{id}")
    public ResponseEntity<Airplane> removeAirplaneByID(@PathVariable Integer id)    {
        Airplane airplane = airplaneService.getAirplaneById(id);

        if(airplane == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        airplaneService.deleteAirplaneById(id);
        return new ResponseEntity<Airplane>(airplane, HttpStatus.OK);
    }
}
