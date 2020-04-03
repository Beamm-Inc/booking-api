package com.beamm.flightbooking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "airports")
public class Airport
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airportid")
    private Integer airportID;

    @Column(name = "airportname")
    private String airportName;

    @Column(name = "airportcode")
    private String airportCode;

    @Column(name = "airportcity")
    private String airportCity;

    // TODO: Add terminals

}
