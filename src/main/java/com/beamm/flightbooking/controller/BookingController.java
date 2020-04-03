package com.beamm.flightbooking.controller;

import com.beamm.flightbooking.dto.Bookingdto;
import com.beamm.flightbooking.model.*;
import com.beamm.flightbooking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;


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

    @GetMapping("flight/search")
    public String searchbooking() {
        return "flight/search";
    }


    @PostMapping("flight/search{bookingrefrence}")
    public ModelAndView searchBookingSubmitBtn(@RequestParam String bookingReference) {
        ModelAndView mav = new ModelAndView();
        Booking booking = bookingService.getByBookingByBookingReference(bookingReference);
        mav.addObject("booking", booking);
        System.out.println(bookingReference);
        mav.setViewName("flight/search");

        return mav;
    }

    @GetMapping("/flight/new/{flightId}/{passenger}/{trip}/{price}")
    public String getBookingForm(@PathVariable Integer flightId, @PathVariable Passenger passenger, @PathVariable Trip trip,
                                 @PathVariable double price
            , Model model) {
        Flight flight = flightService.getFlightById(flightId);

        if (flight != null) {
            Bookingdto bookingdto = new Bookingdto();
            bookingdto.setPassenger(passenger);
            bookingdto.setTrip(trip);


            model.addAttribute("bookingdto", bookingdto);

            return "flight/new";
        }
        return "searchresult";
    }

    //// to check for deletion ->second method
    @GetMapping(value = {"/delete/{bookingId}"})
    public HttpStatus deleteBooking(@PathVariable Integer bookingId, Model model) throws Exception {
        bookingService.deleteBookingById(bookingId);
        return HttpStatus.FORBIDDEN;
    }
    //   @GetMapping(value = {"/delete/{bookingId}"})
//    public String deleteBooking(@PathVariable Integer bookingId, Model model) throws Exception {
//        bookingService.deleteBookingById(bookingId);
//        //return "booking/search"; }
//


    @PostMapping("/flight/successbooking")
    public String userNewBookingForm(@ModelAttribute("bookingdto") Bookingdto bookingdto,
                                     Model model, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("bookingdto", bookingdto);

            return "flight/new";
        }

        Booking booking = new Booking();
        Passenger passenger = bookingdto.getPassenger();
        //Customer customer = new Customer();
        //passenger.setPassportNumber(bookingdto.getPassenger().getPassportNumber());
        //passenger.setPerson(bookingdto.getCustomer().getPerson());
        //bookingService.addPasseneger(passenger);
        booking.setTrips(bookingdto.getTrip());
        booking.setBookingReference(bookingService.randomAlphaNumericBookingRef(14));


        booking = bookingService.saveBooking(booking);


        return "flight/successbooking";


    }


}
