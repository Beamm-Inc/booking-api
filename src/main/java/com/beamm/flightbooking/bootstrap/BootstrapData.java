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

    private final AirportService airportService;

    private  final TripService tripService;
    private  final BookingService bookingService;
    private  final PersonService personService;
    private final CustomerService customerService;

    public BootstrapData(AirportService airportService, AirplaneService airplaneService, TripService tripService, BookingService bookingService, PersonService personService, CustomerService customerService) {        
    this.airportService = airportService;
    this.airplaneService = airplaneService;
    this.tripService = tripService;
    this.bookingService = bookingService;
    this.personService = personService;
    this.customerService = customerService;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Loading Airplanes");

        Airplane airplane = new Airplane();

        airplane.setAirplaneModel("Ethiopian");
        airplane.setAirplaneModel("777-200LR");
        airplane.setAirplaneSerialNumber("ET-ANN");
        airplane.setEconomyClassSeats(278);
        airplane.setBusinessClassSeats(34);
        airplane.setFirstClassSeats(0);
        airplaneService.saveAirplane(airplane);

        airplane.setAirplaneModel("A350-900 ");
        airplane.setAirplaneSerialNumber("kcds");
        airplane.setBusinessClassSeats(14);
        airplane.setEconomyClassSeats(73);
        airplane.setFirstClassSeats(0);
        airplaneService.saveAirplane(airplane);

        System.out.println("Total Airplanes: ");
        System.out.println(airplaneService.getAllAirplanesList());


        Airport airport = new Airport();

        airport.setAirportCity("Addis Ababa");
        airport.setAirportCode("ADD");
        airport.setAirportName("Bole International Airport");
        airportService.saveAirport(airport);

        airport.setAirportCity("New York");
        airport.setAirportCode("JFK");
        airport.setAirportName("John F Kennedy International Airport");
        airportService.saveAirport(airport);

        airport.setAirportCity("Chicago");
        airport.setAirportCode("ORD");
        airport.setAirportName("Chicago O'Hare International Airport");
        airportService.saveAirport(airport);

        airport.setAirportCity("Beijing");
        airport.setAirportCode("PEK");
        airport.setAirportName("Beijing Capital International Airport");
        airportService.saveAirport(airport);

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
