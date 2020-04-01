package com.beamm.flightbooking.repository;

import com.beamm.flightbooking.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdressRepository extends JpaRepository<Address, Integer> {
}
