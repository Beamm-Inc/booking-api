package com.beamm.flightbooking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @OneToOne(cascade = CascadeType.PERSIST)
    private  Person person;

}
