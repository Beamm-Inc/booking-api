package com.beamm.flightbooking.controller;

import com.beamm.flightbooking.dto.Flightdto;
import com.beamm.flightbooking.model.Airport;
import com.beamm.flightbooking.model.ScheduledFlight;
import com.beamm.flightbooking.repository.AirportRepository;
import com.beamm.flightbooking.service.ScheduledFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(ScheduledFlightController.BASE_URL)
public class ScheduledFlightController {
    @Autowired
    ScheduledFlightService scheduledFlightService;

     @Autowired
    AirportRepository airportRepository;

    public static final String BASE_URL = "/api/v1/scheduledflight";

    @GetMapping("/{id}")
    public ScheduledFlight getScheduledFlightById(@PathVariable Integer id) {

        return scheduledFlightService.getScheduledFlightById(id);
    }

    @GetMapping("/flightnumber/{flightnumber}")
    public ScheduledFlight getScheduledFlightByFlightNumber(@PathVariable String flightNumber) {

        return scheduledFlightService.getScheduledFlightByFlightNumber(flightNumber);
    }

    @GetMapping("/all")
    public List<ScheduledFlight> getAllScheduledFlights() {

        return scheduledFlightService.getAllScheduledFlights();
    }

    @GetMapping
    public Page<ScheduledFlight> list(@RequestParam(defaultValue = "0") int pageNo)
    {
        return scheduledFlightService.getAllScheduledFlightPages(pageNo);
    }

//    @GetMapping

    @PostMapping
    public ResponseEntity<ScheduledFlight> addScheduledFlight(@RequestBody @Valid ScheduledFlight scheduledFlight, BindingResult result, Principal principal)
    {
        return new ResponseEntity<ScheduledFlight>(this.scheduledFlightService.addScheduledFlight(scheduledFlight), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus removeScheduledFlightByID(@PathVariable Integer id)
    {
        return scheduledFlightService.removeScheduledFlightById(id);
    }

    @DeleteMapping("/{flightnumber/{flightnumber}}")
    public HttpStatus removeScheduledFlightByFlightNumber(@RequestBody @Valid String flightNumber, BindingResult result, Principal principal)
    {
        return scheduledFlightService.removeScheduledFlightByFlightNumber(flightNumber);
    }

    @PostMapping( value ={"/search/oneway"})///{fromId}/{toId}"
    public Page<ScheduledFlight> searchScheduledFlightOneWay(@RequestBody @Valid Flightdto flightdto, BindingResult result,
                                                       Principal principal,@RequestParam(defaultValue = "0") int pageNo)
    {
        Airport depart = airportRepository.findById(Integer.parseInt(flightdto.getFrom())).orElse(null);
        Airport arrival = airportRepository.findById(Integer.parseInt(flightdto.getTo())).orElse(null);
        LocalDate departDate = flightdto.getDateOfDeparture();
//        return scheduledFlightService.findAllByFlightOriginAndFlightDestination(depart, arrival, pageNo);
//        return scheduledFlightService.findAllByFlightOrigin_FlightIDAndFlightDestination_FlightID(Integer.parseInt(flightdto.getFrom()), Integer.parseInt(flightdto.getTo()), pageNo);
//        return scheduledFlightService.findAllByFlightOriginAndFlightDestinationAndDepartureDate(depart, arrival, departDate, pageNo);

        return scheduledFlightService.searchScheduledFlightOneWay(depart, arrival, departDate, pageNo);
    }

    @PostMapping( value ={"/search/rounddtrip"})///{fromId}/{toId}"
    public Page<ScheduledFlight> searchScheduledFlightRoundTrip(@RequestBody @Valid Flightdto flightdto, BindingResult result,
                                                       Principal principal,@RequestParam(defaultValue = "0") int pageNo)
    {
        Airport depart = airportRepository.findById(Integer.parseInt(flightdto.getFrom())).orElse(null);
        Airport arrival = airportRepository.findById(Integer.parseInt(flightdto.getTo())).orElse(null);
        LocalDate departDate = flightdto.getDateOfDeparture();
//        return scheduledFlightService.findAllByFlightOriginAndFlightDestination(depart, arrival, pageNo);
//        return scheduledFlightService.findAllByFlightOrigin_FlightIDAndFlightDestination_FlightID(Integer.parseInt(flightdto.getFrom()), Integer.parseInt(flightdto.getTo()), pageNo);
//        return scheduledFlightService.findAllByFlightOriginAndFlightDestinationAndDepartureDate(depart, arrival, departDate, pageNo);
        return scheduledFlightService.searchScheduledFlightOneWay(depart, arrival, departDate, pageNo);
    }
}
