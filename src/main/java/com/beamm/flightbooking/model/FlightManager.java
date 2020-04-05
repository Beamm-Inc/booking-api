package com.beamm.flightbooking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flightmanagers")
public class FlightManager extends Role{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flightmanagerid")
    private Integer flightmanagerID;

    // TODO: Connect it with Security Module
    @Override
    public boolean login(String userName, String password) {
        return false;
    }
}
