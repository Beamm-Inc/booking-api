package com.beamm.flightbooking.controller;

import com.beamm.flightbooking.model.Airport;
import com.beamm.flightbooking.service.AirportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AirportControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AirportService airportService;

    @InjectMocks
    private AirportController airportController;

    ObjectMapper om = new ObjectMapper();

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(airportController)
                .build();
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void list() throws Exception {
        mockMvc.perform(get(AirportController.BASE_URL).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void saveNewAirport() {
    }

    @Test
    public void updateAirportF() {
    }

    @Test
    public void deleteAirportF() throws Exception {
        mockMvc.perform(get(AirportController.BASE_URL+"/delete/1"))
                .andExpect(status().isOk())
                .andReturn();
    }
}