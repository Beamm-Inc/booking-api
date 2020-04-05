package com.beamm.flightbooking.service.impl;

import com.beamm.flightbooking.model.Admin;
import com.beamm.flightbooking.repository.AdminRepository;
import com.beamm.flightbooking.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository;

    @Override
    public Page<Admin> getAllAdminPages(int pageNo) {
        return adminRepository.findAll(PageRequest.of(pageNo,20));
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin getAdminById(Integer id) {
        return adminRepository.findById(id).orElse(null);
    }

    @Override
    public Admin getAdminByUserNameAndPassword(String userName, String password) {

        return adminRepository.getAdminByUserNameAndPassword(userName, password) ;
    }

    @Override
    public HttpStatus deleteAdminById(Integer id) {
        try
        {
            adminRepository.deleteById(id);
            return HttpStatus.valueOf("Deleted admin successfully.");
        }
        catch (Exception ex)
        {
            return HttpStatus.valueOf("No such admin in the system.");
        }
    }

    @Override
    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }
}
