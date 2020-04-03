package com.beamm.flightbooking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Tables;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addressid")
    private  Integer  addressID;
    @Column(name = "street")
    private String street;
    @Column(name ="city")
    private  String  city;
    @Column(name ="state")
    private  String  state;
    @Column(name ="country")
    private  String  country;
    @Column(name ="zip")
    private  String  zip;
    //@Column(name ="zip")


}
