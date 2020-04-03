package com.beamm.flightbooking.controller;

import com.beamm.flightbooking.model.Passenger;
import com.beamm.flightbooking.model.ScheduledFlight;
import com.beamm.flightbooking.service.PassengerService;
import com.beamm.flightbooking.service.ScheduledFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(PassengerController.BASE_URL)
public class ScheduledFlightController {
    public static final String BASE_URL = "/api/v1/scheduledflight";

    @Autowired
    ScheduledFlightService scheduledFlightService;

    @GetMapping("/{id}")
    public ScheduledFlight getScheduledFlightById(@PathVariable Integer id) {

        return scheduledFlightService.getScheduledFlightById(id);
    }

}
