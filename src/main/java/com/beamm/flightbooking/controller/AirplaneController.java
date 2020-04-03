package com.beamm.flightbooking.controller;

import com.beamm.flightbooking.model.Airplane;
import com.beamm.flightbooking.service.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(AirplaneController.BASE_URL)
public class AirplaneController
{
    public static final String BASE_URL = "/api/v1/airplane";

    @Autowired
    AirplaneService airplaneService;

    @GetMapping
    public Page<Airplane> getAllAirplanesPaged(@RequestParam(defaultValue = "0") int pageNo)
    {
        return airplaneService.getAllAirplanesPaged(pageNo);
    }

    @PostMapping
    public ResponseEntity<Airplane> addAirplane(@RequestBody @Valid Airplane airplane, BindingResult result, Principal principal)
    {
        if (result.hasErrors())
        {
            return new ResponseEntity<Airplane>(null,new HttpHeaders(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Airplane>(this.airplaneService.saveAirplane(airplane), HttpStatus.OK);
    }

    @PostMapping(value = {"/edit"})
    public ResponseEntity<Airplane> UpdateAirplane(@Valid @ModelAttribute("airplane") Airplane airplane,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Airplane>(null,new HttpHeaders(),HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<Airplane>(airplaneService.saveAirplane(airplane), HttpStatus.OK);
    }

    @GetMapping(value = {"/delete/{airplaneId}"})
    public ResponseEntity deleteAirplane(@PathVariable Integer airplaneId) {
        airplaneService.deleteAirplaneById(airplaneId);
        return new ResponseEntity<Airplane>(null,new HttpHeaders(),HttpStatus.OK);
    }
}
