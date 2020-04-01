package com.beamm.flightbooking.repository;

import com.beamm.flightbooking.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Integer> {
}
