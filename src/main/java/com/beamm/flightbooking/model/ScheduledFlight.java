package com.beamm.flightbooking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "scheduledflights")
public class ScheduledFlight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scheduledflightid")
    private Integer scheduledflightID;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Flight flight;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Airplane airplane;
    @Column(name = "capacity")
    private int capacity;
    @Column(name = "currentprice")
    private double currentPrice;
    @Column(name = "departuredate")
    private LocalDate departureDate;
    @Column(name = "arrivalDate")
    private LocalDate arrivalDate;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Passenger> passengers;
}
