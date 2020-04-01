package com.beamm.flightbooking.repository;

import com.beamm.flightbooking.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
