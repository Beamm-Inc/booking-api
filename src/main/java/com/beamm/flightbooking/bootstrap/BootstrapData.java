package com.beamm.flightbooking.bootstrap;

import com.beamm.flightbooking.model.*;
import com.beamm.flightbooking.service.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AirplaneService airplaneService;

    private final AirportService airportService;

    private  final TripService tripService;
    private  final BookingService bookingService;
    private  final PersonService personService;
    private final CustomerService customerService;
    private final FlightService flightService;
    private final ScheduledFlightService scheduledFlightService;
    private  final  AddressService addressService;
    private  final  PassengerService passengerService;

    public BootstrapData(AirportService airportService, AirplaneService airplaneService,
                         TripService tripService, BookingService bookingService, PersonService personService,
                         CustomerService customerService, FlightService flightService,
                         ScheduledFlightService scheduledFlightService,AddressService
                                 addressService, PassengerService passengerService) {
    this.airportService = airportService;
    this.airplaneService = airplaneService;
    this.tripService = tripService;
    this.bookingService = bookingService;
    this.personService = personService;
    this.customerService = customerService;
    this.flightService = flightService;
    this.scheduledFlightService = scheduledFlightService;
    this.addressService = addressService;
    this.passengerService = passengerService;
    }

    @Override
    public void run(String... args) throws Exception {


        Airplane airplane = new Airplane();
        airplane.setAirplaneModel("Ethiopian");
        airplane.setAirplaneModel("777-200LR");
        airplane.setAirplaneSerialNumber("ET-ANN");
        airplane.setEconomyClassSeats(278);
        airplane.setBusinessClassSeats(34);
        airplane.setFirstClassSeats(0);
        airplaneService.saveAirplane(airplane);

        Airplane airplane1 = airplane;

        airplane = new Airplane();
        airplane.setAirplaneModel("A350-900 ");
        airplane.setAirplaneSerialNumber("ET-KMR");
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

        Airport airport1 = airport;

        airport = new Airport();
        airport.setAirportCity("New York");
        airport.setAirportCode("JFK");
        airport.setAirportName("John F Kennedy International Airport");
        airportService.saveAirport(airport);

        airport = new Airport();
        airport.setAirportCity("Chicago");
        airport.setAirportCode("ORD");
        airport.setAirportName("Chicago O'Hare International Airport");
        airportService.saveAirport(airport);

        airport = new Airport();
        airport.setAirportCity("Beijing");
        airport.setAirportCode("PEK");
        airport.setAirportName("Beijing Capital International Airport");
        airportService.saveAirport(airport);

        Airport airport2 = airport;

        Address address = new Address();//34,"a",,"c","d","12");
        address.setCity("Chicago");
        address.setState("Illionis");
        address.setStreet("s12N 4th");
        address.setCountry("USA");
        address.setZip("52556");
        addressService.saveAddress(address);
        Person person =new Person();
        person.setFirstName("King");
        person.setMiddleName("John");
        person.setLastName("Musa");
        person.setPhoneNumber("123-344-0987");
        person.setEmail("incbimm@gmial.com,biruk.bekele@gmail.com");
        person.setAddress(address);

        personService.savePerson(person);

        Booking booking = new Booking();

        Trip trip = new Trip();
        trip.setTicketNumber("Eth345679");
        trip.setFlightClass(FlightClass.BUSINESS);
        trip.setSeat("40");
        tripService.saveTrip(trip);
        booking.addTrip(trip);

        Passenger passenger = new Passenger();
        passenger.setPassportNumber("EP234");
        passenger.setPerson(person);
        passenger.addTrip(trip);

       passengerService.savePassenger(passenger);

        booking.setPrice(123.56);
        booking.addPasseneger(passenger);
        booking.setDateTimeOfBooking(LocalDateTime.now());
        booking.setBookingReference(bookingService.randomAlphaNumericBookingRef(12));
        //booking.setCustomer(customer);
        booking.setLuggageAllownace("2");
        bookingService.saveBooking(booking);

        Flight flight = new Flight(1,"ET302",airport1, airport2,
                LocalDate.of(2020,10,10),LocalDate.of(2020,10,10),
                LocalTime.now(),LocalTime.now(),434.3,43434.0);
        flightService.saveFlight(flight);

        ScheduledFlight scheduledFlight1 = new ScheduledFlight(1, flight, airplane1, 50,
                50.0, LocalDate.of(2020, 10, 10),
                LocalDate.of(2020, 10, 10), new ArrayList<Passenger>());

        scheduledFlightService.addScheduledFlight(scheduledFlight1);

        ScheduledFlight scheduledFlight2 = new ScheduledFlight(2, flight, airplane1, 50,
                50.0, LocalDate.of(2020, 10, 13),
                LocalDate.of(2020, 10, 13), new ArrayList<Passenger>());

        scheduledFlightService.addScheduledFlight(scheduledFlight2);

        ScheduledFlight scheduledFlight3 = new ScheduledFlight(3, flight, airplane1, 50,
                50.0, LocalDate.of(2020, 10, 13),
                LocalDate.of(2020, 10, 13), new ArrayList<Passenger>());

        scheduledFlightService.addScheduledFlight(scheduledFlight3);
    }
}
