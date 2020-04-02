package com.beamm.flightbooking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer bookingId;
    @Column(name = "price")
    private Double price;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Customer customer;
}
