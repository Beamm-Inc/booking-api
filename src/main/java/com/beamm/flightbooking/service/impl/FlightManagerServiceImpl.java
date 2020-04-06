package com.beamm.flightbooking.service.impl;

import com.beamm.flightbooking.model.Address;
import com.beamm.flightbooking.model.FlightManager;
import com.beamm.flightbooking.model.Person;
import com.beamm.flightbooking.repository.FlightManagerRepository;
import com.beamm.flightbooking.repository.PersonRepository;
import com.beamm.flightbooking.service.AddressService;
import com.beamm.flightbooking.service.FlightManagerService;
import com.beamm.flightbooking.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightManagerServiceImpl implements FlightManagerService {
    @Autowired
    FlightManagerRepository flightManagerRepository;

    @Autowired
    PersonService personService;

    @Autowired
    AddressService addressService;

    @Override
    public Page<FlightManager> getAllFlightManagerPages(int pageNo) {
        return flightManagerRepository.findAll(PageRequest.of(pageNo, 20));
    }

    @Override
    public List<FlightManager> getAllFlightManagers() {
        return flightManagerRepository.findAll();
    }

    @Override
    public FlightManager getFlightManagerById(Integer id) {
        return flightManagerRepository.findById(id).orElse(null);
    }

    @Override
    public FlightManager getFlightManagerByUserNameAndPassword(String userName, String password) {
        return flightManagerRepository.findByUserNameAndPassword(userName, password);
    }

    @Override
    public ResponseEntity<FlightManager> deleteFlightManagerById(Integer id) {
        try {
            flightManagerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public FlightManager addFlightManager(FlightManager flightManager) {
        addressService.saveAddress(flightManager.getPerson().getAddress());
        personService.savePerson(flightManager.getPerson());
        return flightManagerRepository.save(flightManager);
    }
}
