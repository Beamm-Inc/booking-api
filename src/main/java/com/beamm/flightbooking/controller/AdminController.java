package com.beamm.flightbooking.controller;

import com.beamm.flightbooking.model.Admin;
import com.beamm.flightbooking.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(AdminController.BASE_URL)
public class AdminController {
   @Autowired
    AdminService adminService;

    public static final String BASE_URL = "/api/v1/admin";

    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable Integer id) {

        return adminService.getAdminById(id);
    }

//    @GetMapping("/{username/{username}}")
//    public Admin getAdminByUserNameAndPassword(@PathVariable String username, String password) {
//
//        return adminService.getAdminByUserNameAndPassword(username, password);
//    }

//    @GetMapping("/login")
//    public int loginuservalidation(@PathVariable ("user") String username,
//                                   @PathVariable ("password") String password){
//
//        return profileInterface.loginuservalidate(username, password);
//    }

    @GetMapping("/all")
    public List<Admin> getAllAdmins() {

        return adminService.getAllAdmins();
    }

    @GetMapping
    public Page<Admin> list(@RequestParam(defaultValue = "0") int pageNo)
    {
        return adminService.getAllAdminPages(pageNo);
    }

    @PostMapping
    public ResponseEntity<Admin> saveNewCustomer(@RequestBody @Valid Admin admin, BindingResult result, Principal principal)
    {
        return new ResponseEntity<Admin>(this.adminService.saveAdmin(admin), HttpStatus.OK);
    }
}
