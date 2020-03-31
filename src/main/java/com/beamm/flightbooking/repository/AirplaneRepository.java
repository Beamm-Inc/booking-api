package com.beamm.flightbooking.repository;

import com.beamm.flightbooking.model.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane,Integer>
{
}
