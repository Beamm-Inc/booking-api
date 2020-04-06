package com.beamm.flightbooking.controller;

import com.beamm.flightbooking.dto.Bookingdto;
import com.beamm.flightbooking.model.*;
import com.beamm.flightbooking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;


@RestController
@RequestMapping(BookingController.BASE_URL)
public class BookingController {
    ModelMap model = new ModelMap();
    @Autowired
    BookingService bookingService;

    @Autowired
    FlightService flightService;
    @Autowired
    PassengerService passengerService;
    @Autowired
    ScheduledFlightService scheduledFlightService;
    @Autowired
    PersonService personService;
    @Autowired
    NotificationService notificationService;

    public static final String BASE_URL = "/api/v1/booking";

    @GetMapping("/allbooking")
    public List<Booking> allBooking() {
        return bookingService.getAllBooking();
    }


    //findBooking By booking refernce
    @RequestMapping(value = "/search/{bookingReference}", method = RequestMethod.GET)
    @ResponseBody

    public ResponseEntity<Booking> getbookingByReference(@PathVariable("bookingReference") String bookingReference) {
        Booking booking = bookingService.getByBookingReference(bookingReference);
        if (booking == null) {
            return new ResponseEntity("Booking with given " + bookingReference + "not found", HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity(booking, HttpStatus.OK);
    }


    //// to check for deletion ->
    @GetMapping(value = {"/delete/{bookingId}"})
    public ResponseEntity<Booking> deleteBooking(@PathVariable Integer bookingId, Model model) {
        try {
            bookingService.deleteBookingById(bookingId);
            return new ResponseEntity<Booking>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }


    //== make reservation
    @RequestMapping(value = "/flight/reservation/",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Booking> makeBooking(@RequestBody Bookingdto bookingdto, Integer flghtid,
                                               BindingResult bindingResult,

                                               HttpServletResponse response) {

        System.out.println("------>" + bookingdto);
        ScheduledFlight scheduledFlight = scheduledFlightService.getScheduledFlightById(bookingdto.getFlghtid());
        System.out.println("-- scheduled " + scheduledFlight);
        if (scheduledFlight != null) {

            Booking booking = new Booking();
            Passenger passengers = new Passenger();
            if (scheduledFlight.getPassengers().size() + bookingdto.getPassengers().size() > scheduledFlight.getCapacity()) {
                return new ResponseEntity<Booking>(HttpStatus.BAD_REQUEST);
            }
            Passenger passengerIn = new Passenger();
            Iterator<Passenger> passengerIterator = bookingdto.getPassengers().iterator();
            while (passengerIterator.hasNext()) {
                passengerIn = passengerIterator.next();
                passengers = passengerService.savePassenger(passengerIn);


            }
            //System.out.println("passengers" + passengers);

            Trip trip = new Trip();
            Iterator<Trip> tripIterator = passengers.getTrips().iterator();
            while (tripIterator.hasNext()) {
                trip=tripIterator.next();
                if (trip.getFlightClass().toString().equals("BUSINESS")) {
                    booking.setLuggageAllownace("2 checked bags:23kg , 1 carryonBag");
                }
                else {
                    booking.setLuggageAllownace("1 checked bags:23kg , 1 carryonBag");

                }


            }


            booking.addPasseneger(passengers);
            booking.setBookingReference(bookingService.randomAlphaNumericBookingRef(14));
            booking.setPrice(bookingdto.getPrice());
            booking.setDateTimeOfBooking(LocalDateTime.now());
            booking.setPrice(scheduledFlight.getCurrentPrice());
            scheduledFlight.addPassenger(passengers);
            String body = "Dear " + passengerIn.getPerson().getFirstName() + "," + " thank you for choosing us." +
                    "\nYour booking  is confirmed \nDetails of your flight: " +
                    "\nFrom " + scheduledFlight.getFlight().getOrigin().getAirportCity() +
                    " to " + scheduledFlight.getFlight().getDestination().getAirportCity() +
                    "\nDate: " + scheduledFlight.getDepartureDate() + "\n"+
                    " \nBooking Class: " + trip.getFlightClass() +"\n"+
                    " \nFare: $" + booking.getPrice()  +"\n" +
                    "\nReference Code : " + booking.getBookingReference();

            notificationService.sendNotification("beamminc@gmail.com", passengerIn.getPerson().getEmail(),
                    body, "Booking confirmation");
            return new ResponseEntity<Booking>(bookingService.saveBooking(booking), HttpStatus.OK);
        } else {
            return new ResponseEntity<Booking>(HttpStatus.BAD_REQUEST);

        }

    }


}
