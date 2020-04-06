package com.beamm.flightbooking.controller;

import com.beamm.flightbooking.model.Person;
import com.beamm.flightbooking.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping(PersonController.BASE_URL)
public class PersonController {
    @Autowired
    PersonService personService;

    public static final String BASE_URL = "/api/v1/person";

    @GetMapping
    public ResponseEntity getPerson()  {
        return new ResponseEntity(null,new HttpHeaders(),HttpStatus.FORBIDDEN);
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable Integer id) { return personService.getPersonById(id); }


    @PostMapping

        public ResponseEntity<Person> saveNewPerson(@RequestBody @Valid Person person, BindingResult result, Principal principal)
			 {
            return new ResponseEntity<>(this.personService.savePerson(person), HttpStatus.OK);
        }

}

