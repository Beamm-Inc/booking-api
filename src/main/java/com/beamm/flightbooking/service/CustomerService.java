package com.beamm.flightbooking.service;


import com.beamm.flightbooking.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface CustomerService {
    Page<Customer> getAllCustomerPages(int pageNo);
    List<Customer> getAllCustomers();
    Customer getCustomerById(Integer id);
    HttpStatus deleteCustomerById(Integer id);
    Customer saveCustomer(Customer customer);
    Customer getCustomerByuserNameAndpasswWord(String un,String pw);
}
