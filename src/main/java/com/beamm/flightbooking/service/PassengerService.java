package com.beamm.flightbooking.service;

import com.beamm.flightbooking.model.Passenger;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface PassengerService {
    Page<Passenger> getAllPassengerPages(int pageNo);
    List<Passenger> getAllPassenger();
    Passenger getPassengerById(Integer id);
    Passenger getPassengerByPassport(String passport);
    HttpStatus deletePassengerById(Integer id);
    Passenger savePassenger(Passenger passenger);
    Passenger getPassengerByUserNameAndPassWord(String un,String pw);
}
