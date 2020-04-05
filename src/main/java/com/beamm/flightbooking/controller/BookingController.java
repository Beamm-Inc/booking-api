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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping(BookingController.BASE_URL)
public class BookingController {
    ModelMap model = new ModelMap();
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
    @Autowired
    AddressService addressService;
    @Autowired
    ScheduledFlightService scheduledFlightService;
    @Autowired
    PersonService personService;

    public static final String BASE_URL = "/api/v1/booking";

    @GetMapping("/allbooking")
    public List<Booking> allBooking() {
        return bookingService.getAllBooking();
    }


//    @GetMapping("/search")
//    public String searchbooking() {
//        return "flight/search";
//    }

     //findBooking By booking refernce
    @RequestMapping(value = "/search/{bookingReference}", method = RequestMethod.GET)
    @ResponseBody

     public ResponseEntity<Booking> getbookingByReference(@PathVariable("bookingReference") String bookingReference)  {
        Booking booking = bookingService.getByBookingReference(bookingReference);
        if (booking == null) {
            return  new ResponseEntity("Booking with given "+ bookingReference +"not found",HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity(booking, HttpStatus.OK);
    }


//    @PostMapping("flight/search{bookingReference}")
//    public ModelAndView searchBookingSubmitBtn(@RequestParam String bookingReference) {
//        ModelAndView mav = new ModelAndView();
//        Booking booking = bookingService.getByBookingReference(bookingReference);
//        mav.addObject("booking", booking);
//        System.out.println(bookingReference);
//        mav.setViewName("flight/search");
//
//        return mav;
//    }

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



    @RequestMapping(value = "/flight/reservation/",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Booking> makeBooking( @RequestBody Bookingdto bookingdto,
                                                  BindingResult bindingResult,

                                                  HttpServletResponse response)
    {

        System.out.println("------>" + bookingdto);
                     int flightId = 1;
    // ScheduledFlight scheduledFlight = scheduledFlightService.getScheduledFlightById(flightId);
             //if(scheduledFlight != null){
            // Trip trip  =tripService.saveTrip(bookingdto.getTrip());
             //Address address =addressService.saveAddress(bookingdto.getAddress());
             // Person person = personService.savePerson(bookingdto.getPerson());
              Passenger passenger =new Passenger();
               //passenger.setPerson(person);
              // passenger.addTrip(trip);
            // passengerService.savePassenger(bookingdto.getPassenger());
                 Booking booking = new Booking();
                 //booking.addPasseneger(passenger);
                 booking.setBookingReference(bookingService.randomAlphaNumericBookingRef(14));

                 return new ResponseEntity<>(this.bookingService.saveBooking(booking), HttpStatus.OK);
             //}
             //return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

//    @PostMapping("/flight/bookstart")
//    public String getBookingForm( @ModelAttribute("bookingdto") Bookingdto bookingdto,
//                                  Model model, BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("bookingdto", bookingdto);
//
//            return "flight/new";
//        }
//
//        System.out.println("------>" + bookingdto);
//
//        Booking booking = new Booking();
//        Passenger passenger =  new Passenger();
//        Trip trip = new Trip();
//
//        passenger.addTrip(bookingdto.getTrip());
//        booking.addPasseneger(passenger);
//        booking.setBookingReference(bookingService.randomAlphaNumericBookingRef(14));
//        booking = bookingService.saveBooking(booking);
//
//        return "flight/successbooking";
//    }


}
