package com.beamm.flightbooking.dto;


import com.beamm.flightbooking.model.FlightClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flightdto
{
    private String flightCount;

    private String from;

    private String to;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfDeparture;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfReturn;

    private Integer adult;

    private Integer children;

    private FlightClass flightClass;

}
