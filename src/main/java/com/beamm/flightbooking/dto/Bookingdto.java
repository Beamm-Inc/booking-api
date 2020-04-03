package com.beamm.flightbooking.dto;

import com.beamm.flightbooking.model.Customer;
import com.beamm.flightbooking.model.Passenger;
import com.beamm.flightbooking.model.Trip;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Bookingdto {


    Customer customer;
    Passenger passenger;
    Trip trip;
    private Double price;
    private String luggageAllowance;
}
    //private FlightClass flightClass;



