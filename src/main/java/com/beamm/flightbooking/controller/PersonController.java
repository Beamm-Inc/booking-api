package com.beamm.flightbooking.controller;

import com.beamm.flightbooking.model.Person;
import com.beamm.flightbooking.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping( value ="/")
    public HttpStatus getPerson()  {
        return HttpStatus.FORBIDDEN;
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable Integer id) { return personService.getPersonById(id); }


    @PostMapping
  /*  public Person saveNewPerson(@Valid @ModelAttribute("person") Person person,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return null;
        }
        return personService.savePerson(person);
    }*/
        public ResponseEntity<Person> saveNewPerson(@RequestBody @Valid Person person, BindingResult result, Principal principal)
			 {
            return new ResponseEntity<>(this.personService.savePerson(person), HttpStatus.OK);
        }
    //return new ResponseEntity<>(this.service.createEmployee(employeeDTO), HttpStatus.OK)
}

