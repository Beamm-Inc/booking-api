package com.beamm.flightbooking.controller;

import com.beamm.flightbooking.dto.Flightdto;
import com.beamm.flightbooking.model.Airport;
import com.beamm.flightbooking.model.ScheduledFlight;
import com.beamm.flightbooking.repository.AirportRepository;
import com.beamm.flightbooking.service.ScheduledFlightService;
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
//    public ResponseEntity<ScheduledFlight> removeScheduledFlightByID(@PathVariable Integer id)
//    {
//        return new ResponseEntity<ScheduledFlight>(this.scheduledFlightService.removeScheduledFlightById(id));
//    }

    @DeleteMapping("/{flightnumber/{flightnumber}}")
    public HttpStatus removeScheduledFlightByFlightNumber(@RequestBody @Valid String flightNumber, BindingResult result, Principal principal)
    {
        return scheduledFlightService.removeScheduledFlightByFlightNumber(flightNumber);
    }


    @PostMapping( value ={"/search"})///{fromId}/{toId}"
    public Page<ScheduledFlight> searchScheduledFlight(@RequestBody @Valid Flightdto flightdto, BindingResult result,
                                                       Principal principal,@RequestParam(defaultValue = "0") int pageNo)
    {
        System.out.println("----->Test1 " + Integer.parseInt(flightdto.getFrom()));
        Airport depart = airportRepository.findById(Integer.parseInt(flightdto.getFrom())).orElse(null);
        System.out.println("----->Test2 " + Integer.parseInt(flightdto.getTo()));
        Airport arrival = airportRepository.findById(Integer.parseInt(flightdto.getTo())).orElse(null);
        System.out.println("----->Test3 ");
        return scheduledFlightService.findByFlightOriginAndFlightDestination(depart, arrival,pageNo);
//        if (depart != null && arrival != null)
//        {
//            System.out.println("----->Test4");
////            return new ResponseEntity<ScheduledFlight>(scheduledFlightService.findByFlightOriginAndFlightDestination(depart, arrival), HttpStatus.OK);
//            return scheduledFlightService.findByFlightOriginAndFlightDestination(depart, arrival,pageNo);
//        }
//        else
//        {
//            System.out.println("----->Test5");
//            // TODO: Make sure null is not returned but empty list
//            ScheduledFlight scheduledFlight = null;
//            return new ResponseEntity<ScheduledFlight>(scheduledFlight , HttpStatus.BAD_REQUEST);
//        }
//        // ResponseEntity<ScheduledFlight> searchByFlightOriginAndFlightDestination(Airport depart, Airport arrival);
    }
}
