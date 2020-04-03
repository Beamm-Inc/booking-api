package com.beamm.flightbooking.bootstrap;

import com.beamm.flightbooking.model.Airplane;
import com.beamm.flightbooking.model.Airport;
import com.beamm.flightbooking.service.AirplaneService;
import com.beamm.flightbooking.service.AirportService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AirplaneService airplaneService;
    private final AirportService airportService;

    public BootstrapData(AirplaneService airplaneService, AirportService airportService) {
        this.airplaneService = airplaneService;
        this.airportService = airportService;
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
    }
}
