package com.beamm.flightbooking.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "seatingconfiguration")
public class SeatingConfiguration {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "seatingconfigid")
//    private Integer seatingConfigID;
//
//    @Column(name = "first")
//    private int first;
//    @Column(name = "business")
//    private int business;
//    @Column(name = "economy")
//    private int economy;
//    @OneToMany(cascade = CascadeType.PERSIST)
//    private List<SeatRow> seatMap;
//
//    @Entity
//    private class SeatRow {
//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
//        @Column(name = "seatrowid")
//        private Integer seatRowID;
//
//        @OneToMany(cascade = CascadeType.PERSIST)
//        private List<Seat> seatRow;
//
//        @Entity
//        private class Seat {
//            @Id
//            @GeneratedValue(strategy = GenerationType.IDENTITY)
//            @Column(name = "seatid")
//            private Integer seatID;
//
//            @Column(name = "seatclass")
//            private FlightClass seatClass;
//            @Column(name = "isoccupied")
//            private boolean isOccupied;
//        }
//    }
}
