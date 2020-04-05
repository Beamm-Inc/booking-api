package com.beamm.flightbooking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personid")
    private Integer personID;

    @Column(name = "firstname")
    private String firstName;
    @Column(name = "middlename")
    private String middleName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "birthdate")
    private LocalDate birthDate;

    @Column(name = "username")
    private String userName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "phonenumber")
    private String phoneNumber;
    @Column(name = "email")
    private String email;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Address address = new Address();
}