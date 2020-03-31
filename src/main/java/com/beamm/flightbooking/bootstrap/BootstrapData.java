package com.beamm.flightbooking.bootstrap;

import com.beamm.flightbooking.model.Airplane;
import com.beamm.flightbooking.repository.AirplaneRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AirplaneRepository airplaneRepository;

    public BootstrapData(AirplaneRepository airplaneRepository) {
        this.airplaneRepository = airplaneRepository;
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
        airplaneRepository.save(c1);

        Airplane c2 = new Airplane();

        c2.setAirplaneModel("Ethiopian");
        c2.setAirplaneSerialNumber("kcds");
        c2.setBusinessClassSeats(14);
        c2.setEconomyClassSeats(73);
        c2.setFirstClassSeats(0);
        airplaneRepository.save(c2);


        System.out.println("Total Airplanes: " + airplaneRepository.count());
    }
}
