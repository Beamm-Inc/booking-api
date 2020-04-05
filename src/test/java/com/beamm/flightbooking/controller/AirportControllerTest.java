package com.beamm.flightbooking.controller;

import com.beamm.flightbooking.model.Airport;
import com.beamm.flightbooking.service.AirportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
    public void saveNewAirport() throws Exception {

        Airport airport = new Airport(1,"Bole International Airport","ADD","Addis Ababa");
        String jsonRequest = om.writeValueAsString(airport);
        when(airportService.saveAirport(airport)).thenReturn(airport);
        MvcResult result =  mockMvc.perform(post(AirportController.BASE_URL).content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.airportID", is(airport.getAirportID())))
                .andExpect(jsonPath("$.airportName", is("Bole International Airport")))
                .andExpect(jsonPath("$.airportCode", is("ADD")))
                .andExpect(jsonPath("$.airportCity", is("Addis Ababa")))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertEquals(content, jsonRequest);
    }

    @Test
    public void updateAirportF() throws Exception{
        Airport airport = new Airport(1,"Bole International Airport","ADD","Addis Ababa");
        Airport updateAirport = new Airport(1,"Bole International Airport","ADD","Addis Ababa");

        String jsonRequest = om.writeValueAsString(updateAirport);

        when(airportService.getAirportById(airport.getAirportID())).thenReturn(airport);
        when(airportService.saveAirport(updateAirport)).thenReturn(updateAirport);

        mockMvc.perform(post(AirportController.BASE_URL+"/edit").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    public void deleteAirportF() throws Exception {
        mockMvc.perform(get(AirportController.BASE_URL+"/delete/1"))
                .andExpect(status().isOk())
                .andReturn();
    }
}