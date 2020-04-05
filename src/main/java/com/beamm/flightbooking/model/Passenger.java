package com.beamm.flightbooking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "passengers")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passangerid")
    private  Integer  passengerID;

    @Column(name="passportnumber")
    private String passportNumber;

    @OneToOne(cascade = CascadeType.MERGE)
    private  Person person = new Person();
    @OneToMany(cascade = CascadeType.MERGE)
    private List<Trip>trips = new ArrayList<>();

    public Trip addTrip(Trip trip) {

        boolean isAdded = trips.add(trip);

        return isAdded?trip:null;
    }

}
