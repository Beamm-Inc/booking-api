package com.beamm.flightbooking.controller;

import com.beamm.flightbooking.model.Airplane;
import com.beamm.flightbooking.service.AirplaneService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirplaneControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AirplaneService airplaneService;

    @InjectMocks
    private AirplaneController airplaneController;

    ObjectMapper om = new ObjectMapper();

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(airplaneController)
                .build();
    }

    @Test
    public void addAirplane() throws Exception {
        Airplane airplane = new Airplane(1,"BNG54234CD4","777 Max",4,15,125);
        String jsonRequest = om.writeValueAsString(airplane);
        when(airplaneService.saveAirplane(airplane)).thenReturn(airplane);
        MvcResult result =  mockMvc.perform(post(AirplaneController.BASE_URL).content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.airplaneID", is(airplane.getAirplaneID())))
                .andExpect(jsonPath("$.airplaneSerialNumber", is("BNG54234CD4")))
                .andExpect(jsonPath("$.airplaneModel", is("777 Max")))
                .andExpect(jsonPath("$.firstClassSeats", is(4)))
                .andExpect(jsonPath("$.businessClassSeats", is(15)))
                .andExpect(jsonPath("$.economyClassSeats", is(125)))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertEquals(content, jsonRequest);
    }

    @Test
    public void getAllAirplanesPaged() throws Exception {
        mockMvc.perform(get(AirplaneController.BASE_URL).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getAllAirplanesList() throws Exception {
        mockMvc.perform(get(AirplaneController.BASE_URL+"/all").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getAirplaneById() throws Exception {
        Airplane airplane = new Airplane(1,"BNG54234CD4","777 Max",4,15,125);

        when(airplaneService.getAirplaneById(1)).thenReturn(airplane);

        mockMvc.perform(get(AirplaneController.BASE_URL+"/1").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.airplaneID", is(1)))
                .andExpect(jsonPath("$.airplaneSerialNumber", is("BNG54234CD4")))
                .andExpect(jsonPath("$.airplaneModel", is("777 Max")))
                .andExpect(jsonPath("$.firstClassSeats", is(4)))
                .andExpect(jsonPath("$.businessClassSeats", is(15)))
                .andExpect(jsonPath("$.economyClassSeats", is(125)))
                .andReturn();
        verify(airplaneService, times(1)).getAirplaneById(1);
        verifyNoMoreInteractions(airplaneService);
    }

    @Test
    public void updateAirplane() throws Exception {
        Airplane airplane = new Airplane(1,"BNG54234CD4","777 Max",4,15,125);
        Airplane updatedAirplane = new Airplane(1,"BNG54234CDE","777 Max",4,15,125);

        String jsonRequest = om.writeValueAsString(updatedAirplane);

        when(airplaneService.getAirplaneById(airplane.getAirplaneID())).thenReturn(airplane);
        when(airplaneService.saveAirplane(updatedAirplane)).thenReturn(updatedAirplane);

        mockMvc.perform(post(AirplaneController.BASE_URL+"/edit").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void deleteAirplane() throws Exception {
        mockMvc.perform(get(AirplaneController.BASE_URL+"/delete/1"))
                .andExpect(status().isOk())
                .andReturn();
    }
}