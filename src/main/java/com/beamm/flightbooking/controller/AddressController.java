package com.beamm.flightbooking.controller;


import com.beamm.flightbooking.model.Address;
import com.beamm.flightbooking.model.Airplane;
import com.beamm.flightbooking.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping(AddressController.BASE_URL)
public class AddressController {
    @Autowired
    AddressService addressService;

    public static final String BASE_URL = "/api/v1/address";

    @GetMapping
    public ResponseEntity getAddress()  {
        return new ResponseEntity(null,new HttpHeaders(),HttpStatus.FORBIDDEN);
    }
    @GetMapping("/{id}")
    public Address getAddressById(@PathVariable Integer id) {
        return addressService.getAddressById(id);
    }


    @PostMapping
    public ResponseEntity<Address> saveNewAddress(@RequestBody @Valid Address address, BindingResult result, Principal principal)
    {
        return new ResponseEntity<>(this.addressService.saveAddress(address), HttpStatus.OK);
    }
    /*public Address saveNewAddress(@Valid @ModelAttribute("address") Address address,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return null;
        }
        return addressService.saveAddress(address);
    }*/
}