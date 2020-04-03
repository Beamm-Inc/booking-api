package com.beamm.flightbooking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "luggageallowances")
public class LuggageAllowance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "luggageid")
    private Integer luggageID;

    @Column(name = "carryons")
    private int carryOns;

    @Column(name = "carryonweight")
    private double carryOnWeight;

    @Column(name = "checkedbags")
    private int checkedBags;

    @Column(name = "checkedbagweight")
    private double checkedBagWeight;
}
