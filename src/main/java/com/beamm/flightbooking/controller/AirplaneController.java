package com.beamm.flightbooking.controller;

import com.beamm.flightbooking.model.Airplane;
import com.beamm.flightbooking.model.Airplane;
import com.beamm.flightbooking.service.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping(AirplaneController.BASE_URL)
public class AirplaneController
{
    public static final String BASE_URL = "/api/v1/airplane";

    @Autowired
    AirplaneService airplaneService;

    @GetMapping
    public Page<Airplane> list(@RequestParam(defaultValue = "0") int pageNo)
    {
        return airplaneService.getAllAirplanesPaged(pageNo);
    }

    @PostMapping
    /*public Airplane addNewAirplane(@Valid @ModelAttribute("airplane") Airplane airplane,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors())
        {
            return null;
        }
        return airplaneService.saveAirplane(airplane);
    }*/


    public ResponseEntity<Airplane> saveNewPerson(@RequestBody @Valid Airplane airplane, BindingResult result, Principal principal)
    {
        if (result.hasErrors())
        {
            return null;
        }
        return new ResponseEntity<>(this.airplaneService.saveAirplane(airplane), HttpStatus.OK);
    }

    @PostMapping(value = {"/edit"})
    public Airplane UpdateAirplane(@Valid @ModelAttribute("airplane") Airplane airplane,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return null;
        }
        return airplaneService.saveAirplane(airplane);
    }

    @GetMapping(value = {"/delete/{airplaneId}"})
    public void deleteAirplane(@PathVariable Integer airplaneId) {
        airplaneService.deleteAirplaneById(airplaneId);
    }
}
