package com.beamm.flightbooking.controller;

import com.beamm.flightbooking.dto.Bookingdto;
import com.beamm.flightbooking.model.Booking;
import com.beamm.flightbooking.model.Flight;
import com.beamm.flightbooking.model.Passenger;
import com.beamm.flightbooking.model.Trip;
import com.beamm.flightbooking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping(BookingController.BASE_URL)
public class BookingController {

    @Autowired
    BookingService bookingService;
    @Autowired
    TripService tripService;
    @Autowired
    FlightService flightService;
    @Autowired
    PassengerService passengerService;
    @Autowired
    CustomerService customerService;
    public static final String BASE_URL = "/api/v1/booking";

    @GetMapping(value = "/search")
    public String searchbooking() {
        return "booking/search";
    }


    @PostMapping(value = "/search{bookingrefrence}")
    public ModelAndView searchBookingSubmitBtn(@RequestParam String bookingReference) {
        ModelAndView mav = new ModelAndView();
        Booking booking = bookingService.getByBookingByBookingReference(bookingReference);
        mav.addObject("booking",booking);
        System.out.println(bookingReference);
        mav.setViewName("flight/booking/search");

        return mav;
    }

    @GetMapping(value = {"/flightbooking/new/{flightId}/{passenger}/{trip}/{price}"})
    public String displayNewBookingForm(@PathVariable Integer flightId, @PathVariable Passenger passenger, @PathVariable Trip trip,
                                        @PathVariable double price
            , Model model)
    {
        Flight flight = flightService.getFlightById(flightId);

        if (flight != null) {
            Bookingdto bookingdto = new Bookingdto();
            bookingdto.setPassenger(passenger);
            bookingdto.setTrip(trip);


            model.addAttribute("bookingdto",bookingdto);

            return "booking/new";
        }
        return "searchresult";
    }


     public HttpStatus deleteBooking(@PathVariable Integer bookingId, Model model) throws Exception {
         bookingService.deleteBookingById(bookingId);
         return HttpStatus.FORBIDDEN;
     }

//    @GetMapping(value = {"/delete/{bookingId}"})
//    public String deleteBooking(@PathVariable Integer bookingId, Model model) throws Exception {
//        bookingService.deleteBookingById(bookingId);
//        //return "booking/search"; }
//
    }


