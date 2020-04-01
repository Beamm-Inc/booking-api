package com.beamm.flightbooking.controller;

import com.beamm.flightbooking.model.Airplane;
import com.beamm.flightbooking.model.Customer;
import com.beamm.flightbooking.model.Person;
import com.beamm.flightbooking.service.AirplaneService;
import com.beamm.flightbooking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {
    public static final String BASE_URL = "/api/v1/customer";

    @Autowired
    CustomerService customerService;

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Integer id) {

        return customerService.getCustomerById(id);
    }

    @GetMapping("/")
    public List<Customer> getAllCustomers() {

        return customerService.getAllCustomers();
    }
    @GetMapping
    public Page<Customer> list(@RequestParam(defaultValue = "0") int pageNo)
    {
        return customerService.getAllCustomerPages(pageNo);
    }
    @PostMapping
    public ResponseEntity<Customer> saveNewCustomer(@RequestBody @Valid Person person, BindingResult result, Principal principal)
    {
        Customer customer = new Customer();
        customer.setPersonID(person.getPersonID());
        return new ResponseEntity<>(this.customerService.saveCustomer(customer), HttpStatus.OK);
    }
}
