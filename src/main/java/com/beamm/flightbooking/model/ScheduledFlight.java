package com.beamm.flightbooking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
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

    // TODO: Lock passenger list for concurrent adding of passengers to the list --> check if it full
    @OneToMany(cascade = CascadeType.MERGE)
    private List<Passenger> passengers = new ArrayList<>(capacity);

    public ScheduledFlight(Flight flight, Airplane airplane, double currentPrice, LocalDate departureDate, LocalDate arrivalDate)
    {
        this.flight = flight;
        this.airplane = airplane;
        this.capacity = airplane.getFirstClassSeats() + airplane.getAirplaneID() + airplane.getEconomyClassSeats();
        this.currentPrice = currentPrice;   //flight.getNominalPrice() + inflation;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;

        passengers = new ArrayList<>(this.capacity);
    }
}
