package com.beamm.flightbooking.service;

import com.beamm.flightbooking.model.Person;

public interface PersonService  {
    Person getPersonById(Integer id);
    Person savePerson(Person person);
//    Person getPersonByEmail(String email);
//    Person getPersonByName(String firstName, String lastName);
}
