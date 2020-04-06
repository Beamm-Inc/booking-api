package com.beamm.flightbooking.service.impl;

import com.beamm.flightbooking.model.Address;
import com.beamm.flightbooking.model.Passenger;
import com.beamm.flightbooking.repository.AddressRepository;
import com.beamm.flightbooking.repository.PassengerRepository;
import com.beamm.flightbooking.repository.PersonRepository;
import com.beamm.flightbooking.repository.TripRepository;
import com.beamm.flightbooking.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {
    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    PersonRepository personRepository;
@Autowired
    TripRepository tripRepository;
    @Override
    public Page<Passenger> getAllPassengerPages(int pageNo) {
        return passengerRepository.findAll(PageRequest.of(pageNo,20));
    }

    @Override
    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    @Override
    public Passenger getPassengerById(Integer id) {
        return passengerRepository.findById(id).orElse(null);
    }

    @Override
    public Passenger getPassengerByPassport(String passport) {
        List<Passenger> passengers = passengerRepository.findAll();
        Iterator<Passenger> iterator = passengers.iterator();
        Passenger passenger;

        while (iterator.hasNext())
        {
            passenger = iterator.next();

            if(passenger.getPassportNumber().equals(passport)){
                return passenger;
            }
        }

        return null;
    }

    @Override
    public HttpStatus deletePassengerById(Integer id) {
        try{
            passengerRepository.deleteById(id);
            return HttpStatus.valueOf("Passenger successfully removed.");
        }
        catch(Exception  ex){
            return HttpStatus.FORBIDDEN;
        }
    }

    @Override
    public Passenger savePassenger(Passenger passenger) {
        addressRepository.save(passenger.getPerson().getAddress());
        personRepository.save(passenger.getPerson());
        tripRepository.saveAll(passenger.getTrips());
        return passengerRepository.save(passenger);
    }

    // TODO: Implement
    @Override
    public Passenger getPassengerByUserNameAndPassWord(String userName, String password) {
//        Passenger passenger;
//        List<Passenger> passengers = passengerRepository.findAll();
//        Iterator<Passenger> iterator = passengers.iterator();
//
//        while (iterator.hasNext())
//        {
//
//        }
        return null;
    }
}
