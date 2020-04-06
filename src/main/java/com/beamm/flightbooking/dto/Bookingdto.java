package com.beamm.flightbooking.dto;

import com.beamm.flightbooking.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Bookingdto {
    Address address = new Address();
    Person person;
    Integer flghtid;
    List<Passenger> passengers;
    Trip trip;
    private Double price;
    private String luggageAllowance;
}




