package com.beamm.flightbooking.bootstrap;

import com.beamm.flightbooking.model.*;
import com.beamm.flightbooking.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AirplaneService airplaneService;
    private  final TripService tripService;
    private  final BookingService bookingService;
    private  final PersonService personService;
    private final CustomerService customerService;

    public BootstrapData(AirplaneService airplaneService, TripService tripService, BookingService bookingService, PersonService personService, CustomerService customerService) {
        this.airplaneService = airplaneService;
        this.tripService = tripService;
        this.bookingService = bookingService;
        this.personService = personService;
        this.customerService = customerService;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Loading Airplanes");

        Airplane c1 = new Airplane();

        c1.setAirplaneModel("Ethiopian");
        c1.setAirplaneSerialNumber("kcds");
        c1.setBusinessClassSeats(14);
        c1.setEconomyClassSeats(73);
        c1.setFirstClassSeats(0);
        airplaneService.saveAirplane(c1);

        Airplane c2 = new Airplane();

        c2.setAirplaneModel("Kenyan");
        c2.setAirplaneSerialNumber("kcds");
        c2.setBusinessClassSeats(14);
        c2.setEconomyClassSeats(73);
        c2.setFirstClassSeats(0);
        airplaneService.saveAirplane(c2);


        System.out.println("Total Airplanes: ");
        System.out.println(airplaneService.getAllAirplanesList());
        Person person =new Person();
        person.setFirstName("abebe");
        person.setMiddleName("john");
        person.setLastName("aboo");
        personService.savePerson(person);

       Customer customer = new Customer();
       //customer.setPerson(person);
       //customerService.saveCustomer(customer);
        Trip trip = new Trip();
        trip.setTicketNumber("Eth345679");
        trip.setFlightClass(FlightClass.BUSINESS);
        trip.setSeat("40");
        tripService.saveTrip(trip);

        Booking booking = new Booking();
        booking.setTrips(trip);
        booking.setPrice(123.56);
        booking.setDateTimeOfBooking(LocalDateTime.now());
        booking.setBookingReference(bookingService.randomAlphaNumericBookingRef(12));
        //booking.setCustomer(customer);
        booking.setLuggageAllownace("2");
        bookingService.saveBooking(booking);

    }
}
