package com.beamm.flightbooking.controller;

import com.beamm.flightbooking.model.Address;
import com.beamm.flightbooking.service.AddressService;

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
public class AddressControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AddressService addressService;

    @InjectMocks
    private AddressController addressController;

    ObjectMapper om = new ObjectMapper();
    Address address = new Address(1,"1000N St", "Fairfield", "Iowa", "USA", "52557");

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(addressController)
                .build();
    }

    @Test
    public void getAddress() throws Exception{
        MvcResult result =  mockMvc.perform(get(AddressController.BASE_URL).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int content = result.getResponse().getStatus();
        assertEquals(content, HttpStatus.FORBIDDEN.value());

    }

    @Test
    public void getAddressById ()throws Exception{
        when(addressService.getAddressById(1)).thenReturn(address);
        mockMvc.perform(get(AddressController.BASE_URL+"/1").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.addressID", is(1)))
                .andExpect(jsonPath("$.street", is("1000N St")))
                .andExpect(jsonPath("$.city", is("Fairfield")))
                .andExpect(jsonPath("$.state", is("Iowa")))
                .andExpect(jsonPath("$.country", is("USA")))
                .andExpect(jsonPath("$.zip", is("52557")))
                .andReturn();

        verify(addressService, times(1)).getAddressById(1);
        verifyNoMoreInteractions(addressService);
    }

    @Test
    public void saveNewAddress() throws Exception{
        String jsonRequest = om.writeValueAsString(address);
        when(addressService.saveAddress(address)).thenReturn(address);

        MvcResult result =  mockMvc.perform(post(AddressController.BASE_URL).content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.addressID", is(1)))
                .andExpect(jsonPath("$.street", is("1000N St")))
                .andExpect(jsonPath("$.city", is("Fairfield")))
                .andExpect(jsonPath("$.state", is("Iowa")))
                .andExpect(jsonPath("$.country", is("USA")))
                .andExpect(jsonPath("$.zip", is("52557")))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        assertEquals(content, jsonRequest);
    }


}
