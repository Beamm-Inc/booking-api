package com.beamm.flightbooking.service;

import com.beamm.flightbooking.model.Admin;
import com.beamm.flightbooking.model.ScheduledFlight;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface AdminService {
    Page<Admin> getAllAdminPages(int pageNo);
    List<Admin> getAllAdmins();
    Admin getAdminById(Integer id);
    Admin getAdminByUserNameAndPassword(String userName, String password);
    HttpStatus deleteAdminById(Integer id);
    Admin saveAdmin(Admin admin);
}
