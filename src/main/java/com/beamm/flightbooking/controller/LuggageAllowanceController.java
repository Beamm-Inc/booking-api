package com.beamm.flightbooking.controller;

import com.beamm.flightbooking.model.LuggageAllowance;
import com.beamm.flightbooking.service.LuggageAllowanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping(LuggageAllowanceController.BASE_URL)
public class LuggageAllowanceController {
    @Autowired
    LuggageAllowanceService luggageAllowanceService;

    public static final String BASE_URL = "/api/v1/luggageallowance";

    @GetMapping("/{id}")
    public LuggageAllowance getLuggageAllowanceById(Integer id)
    {
        return luggageAllowanceService.getLuggageAllowanceById(id);
    }

    // TODO: needs implementation
//    @GetMapping("/ticket/{ticket}")
//    public LuggageAllowance getLuggageAllowanceByTicket(@PathVariable String ticket)
//    {
//        return luggageAllowanceService.getLuggageAllowanceById(ticket);
//    }

    @PostMapping
    public ResponseEntity<LuggageAllowance> saveLuggageAllowance(@RequestBody @Valid LuggageAllowance luggageAllowance, BindingResult result, Principal principal)
    {
        if (result.hasErrors())
        {
            return null;
        }
        return new ResponseEntity<LuggageAllowance>(luggageAllowanceService.saveLuggageAllowance(luggageAllowance), HttpStatus.OK);
    }
}
