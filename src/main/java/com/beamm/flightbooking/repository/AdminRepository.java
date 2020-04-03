package com.beamm.flightbooking.repository;

import com.beamm.flightbooking.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    @Query("select u from Admin u where u.userName=?1 and u.password=?1")
    Admin getAdminByUserNameAndPassword(String userName, String password);
}
