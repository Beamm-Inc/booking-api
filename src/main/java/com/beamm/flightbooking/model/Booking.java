package com.beamm.flightbooking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookingid")
    private Integer bookingId;
    @Column(name = "price")
    private Double price;
    @Column(name = "datetimeofbooking")
    private LocalDateTime dateTimeOfBooking;
    @Column(name = "bookingReference")
    private String bookingReference;
    @Column(name = "luggageAllownace")
    private String luggageAllownace;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Customer customer;
   // @OneToMany(cascade = CascadeType.PERSIST)
    //private List<Trip>trips;
    @OneToMany (cascade = CascadeType.MERGE)
    private List<Trip> trips = new ArrayList<>();
//    @ManyToOne(cascade = CascadeType.PERSIST)
//    private Passenger passenger;
    @OneToMany(cascade = CascadeType.MERGE)
    private List<Passenger> passengers =new ArrayList<>();

    public Passenger addPasseneger(Passenger passenger) {

        boolean isAdded = passengers.add(passenger);

        return isAdded?passenger:null;
    }

    public Trip addTrip(Trip trip) {

        boolean isAdded = trips.add(trip);

        return isAdded?trip:null;
    }

}
