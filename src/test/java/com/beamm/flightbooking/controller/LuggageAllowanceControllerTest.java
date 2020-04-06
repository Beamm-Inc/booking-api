package com.beamm.flightbooking.controller;

import com.beamm.flightbooking.model.LuggageAllowance;
import com.beamm.flightbooking.service.AddressService;
import com.beamm.flightbooking.service.LuggageAllowanceService;

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
public class LuggageAllowanceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private LuggageAllowanceService luggageAllowanceService;

    @InjectMocks
    private LuggageAllowanceController luggageAllowanceController;

    ObjectMapper om = new ObjectMapper();
    LuggageAllowance luggageAllowance = new LuggageAllowance(1,2,13.5,1,49.9);

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(luggageAllowanceController)
                .build();
    }

    @Test
    public void  getLuggageAllowanceById() throws Exception{
        when(luggageAllowanceService.getLuggageAllowanceById(1)).thenReturn(luggageAllowance);
        mockMvc.perform(get(LuggageAllowanceController.BASE_URL+"/1").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.luggageID", is(1)))
                .andExpect(jsonPath("$.carryOns", is(2)))
                .andExpect(jsonPath("$.carryOnWeight", is(13.5)))
                .andExpect(jsonPath("$.checkedBags", is(1)))
                .andExpect(jsonPath("$.checkedBagWeight", is(49.9)))
                .andReturn();
        verify(luggageAllowanceService, times(1)).getLuggageAllowanceById(1);
        verifyNoMoreInteractions(luggageAllowanceService);

    }

    @Test
    public void saveLuggageAllowance() throws Exception{
        String jsonRequest = om.writeValueAsString(luggageAllowance);
        when(luggageAllowanceService.saveLuggageAllowance(luggageAllowance)).thenReturn(luggageAllowance);

        MvcResult result =  mockMvc.perform(post(LuggageAllowanceController.BASE_URL).content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.luggageID", is(1)))
                .andExpect(jsonPath("$.carryOns", is(2)))
                .andExpect(jsonPath("$.carryOnWeight", is(13.5)))
                .andExpect(jsonPath("$.checkedBags", is(1)))
                .andExpect(jsonPath("$.checkedBagWeight", is(49.9)))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        assertEquals(content, jsonRequest);

    }

}
