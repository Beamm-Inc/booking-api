package com.beamm.flightbooking.service.impl;

import com.beamm.flightbooking.model.Passenger;
import com.beamm.flightbooking.repository.PassengerRepository;
import com.beamm.flightbooking.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {
    @Autowired
    PassengerRepository passengerRepository;

    //TODO: implement
    @Override
    public Page<Passenger> getAllPassengerPages(int pageNo) {
        return null;
    }

    @Override
    public List<Passenger> getAllPassenger() {
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
            return HttpStatus.valueOf("Deleted passenger successfully.");
        }
        catch(Exception  ex){
            return HttpStatus.FORBIDDEN;
        }
    }

    // TODO: Shouldn't the argument be of type Person --> Same for Customer
    @Override
    public Passenger savePassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }
//    public Passenger savePassenger(Person person) {
//        return passengerRepository.save(passenger);
//    }

    // TODO: Implement
    @Override
    public Passenger getPassengerByUserNameAndPassWord(String un, String pw) {
        return null;
    }
}
