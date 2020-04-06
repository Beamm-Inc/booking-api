package com.beamm.flightbooking.controller;

import com.beamm.flightbooking.model.FlightClass;
import com.beamm.flightbooking.model.Meal;
import com.beamm.flightbooking.model.Trip;
import com.beamm.flightbooking.service.AddressService;
import com.beamm.flightbooking.service.TripService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
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
public class TripControllerTest {


    private MockMvc mockMvc;

    @Mock
    private TripService tripService;

    @InjectMocks
    private TripController tripController;

    ObjectMapper om = new ObjectMapper();
   // FlightClass flightClass = new FlightClass();

    Trip trip = new Trip(1,"50", Meal.VEGIE, "4FFCK",FlightClass.ECONOMY);

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(tripController)
                .build();
    }

    @Test
    public void getTripById ()throws Exception{

        when(tripService.getTripById(1)).thenReturn(trip);
        mockMvc.perform(get(TripController.BASE_URL+"/1").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tripID", is(1)))
                .andExpect(jsonPath("$.seat", is("50")))
                .andExpect(jsonPath("$.meal", is("vegie")))
                .andExpect(jsonPath("$.ticketNumber", is("4FFCK")))
                .andExpect(jsonPath("$.flightClass", is("economy")))
                .andReturn();

        verify(tripService, times(1)).getTripById(1);
        verifyNoMoreInteractions(tripService);
    }

    @Test
    public void saveNewTrip() throws Exception{
        String jsonRequest = om.writeValueAsString(trip);
        when(tripService.saveTrip(trip)).thenReturn(trip);
        MvcResult result =  mockMvc.perform(post(TripController.BASE_URL).content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tripID", is(1)))
                .andExpect(jsonPath("$.seat", is("50")))
                .andExpect(jsonPath("$.meal", is("vegie")))
                .andExpect(jsonPath("$.ticketNumber", is("4FFCK")))
                .andExpect(jsonPath("$.flightClass", is("economy")))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertEquals(content, jsonRequest);

    }

    @Test
    public void getByTicketNumber() throws Exception{
        when(tripService.getByTicketNumber("50")).thenReturn(trip);
        mockMvc.perform(get(TripController.BASE_URL+"/ticket/50").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tripID", is(1)))
                .andExpect(jsonPath("$.seat", is("50")))
                .andExpect(jsonPath("$.meal", is("vegie")))
                .andExpect(jsonPath("$.ticketNumber", is("4FFCK")))
                .andExpect(jsonPath("$.flightClass", is("economy")))
                .andReturn();
        verify(tripService, times(1)).getByTicketNumber("50");
        verifyNoMoreInteractions(tripService);
    }

    @Test
    public void getAllTrips() throws Exception{

    }

}
