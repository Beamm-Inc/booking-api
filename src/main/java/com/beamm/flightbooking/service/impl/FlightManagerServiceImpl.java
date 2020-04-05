//package com.beamm.flightbooking.service.impl;
//
//import com.beamm.flightbooking.model.FlightManager;
//import com.beamm.flightbooking.repository.FlightManagerRepository;
//import com.beamm.flightbooking.service.FlightManagerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class FlightManagerServiceImpl implements FlightManagerService {
//    @Autowired
//    FlightManagerRepository flightManagerRepository;
//
//    @Override
//    public Page<FlightManager> getAllFlightManagerPages(int pageNo) {
//        return flightManagerRepository.findAll(PageRequest.of(pageNo, 20));
//    }
//
//    @Override
//    public List<FlightManager> getAllFlightManagers() {
//        return flightManagerRepository.findAll();
//    }
//
//    @Override
//    public FlightManager getFlightManagerById(Integer id) {
//        return flightManagerRepository.findById(id).orElse(null);
//    }
//
////    @Override
////    public FlightManager getFlightManagerByUserNameAndPassword(String userName, String password) {
////        return getFlightManagerByUserNameAndPassword(userName, password);
////    }
//
//    @Override
//    public HttpStatus deleteFlightManagerById(Integer id) {
//        try {
//            flightManagerRepository.deleteById(id);
//            return HttpStatus.valueOf("Successfully deleted flight manager.");
//        }
//        catch (Exception ex)
//        {
//            return HttpStatus.valueOf("No such record in the system.");
//        }
//    }
//
//    @Override
//    public FlightManager saveFlightManager(FlightManager flightManager) {
//        return flightManagerRepository.save(flightManager);
//    }
//}
