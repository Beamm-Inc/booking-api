package com.beamm.flightbooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="trips")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Integer  tripID;
    @Column(name = "seats")
    private  String  seat;
    @Column(name = "meals")
    private  Meal meal;
    @Column(name = "ticketnumbers")
    private  String  ticketNumber;
    // for fight class we can use to make in int if the json not working
    @Column(name = "flightclasses")
    private  FlightClass  flightClass;

}
