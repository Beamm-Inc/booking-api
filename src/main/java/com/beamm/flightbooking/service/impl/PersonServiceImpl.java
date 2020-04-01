package com.beamm.flightbooking.service.impl;

import com.beamm.flightbooking.model.Person;
import com.beamm.flightbooking.repository.PersonRepository;
import com.beamm.flightbooking.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository personRepository;
    @Override
    public Person getPersonById(Integer id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }


}
