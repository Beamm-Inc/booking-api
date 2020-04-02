package com.beamm.flightbooking.service.impl;

import com.beamm.flightbooking.model.Address;
import com.beamm.flightbooking.repository.AddressRepository;
import com.beamm.flightbooking.repository.TripRepository;
import com.beamm.flightbooking.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressRepository adressRepository;


    @Override
    public Address getAddressById(Integer id) {
        return  adressRepository.findById(id).orElse(null);
    }

    @Override
    public Address saveAddress(Address address) {
        return adressRepository.save(address);
    }
}
