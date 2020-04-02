package com.beamm.flightbooking.service.impl;

import com.beamm.flightbooking.model.Customer;
import com.beamm.flightbooking.repository.CustomerRepository;
import com.beamm.flightbooking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;


    //TODO: implement
    @Override
    public Page<Customer> getAllCustomerPages(int pageNo) {
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Integer id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public HttpStatus deleteCustomerById(Integer id) {
        try {
            customerRepository.deleteById(id);
            return HttpStatus.valueOf("Deleted Successfully");

        } catch (Exception ex) {
            return HttpStatus.valueOf("The customer does not exist");
        }
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    //TODO: implement
    @Override
    public Customer getCustomerByUserNameAndPassWord(String un, String pw) {
        return null;
    }
}
