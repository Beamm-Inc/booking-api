package com.beamm.flightbooking.controller;

import com.beamm.flightbooking.model.Address;
import com.beamm.flightbooking.model.Person;
import com.beamm.flightbooking.service.PersonService;

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

import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonController personController;

    ObjectMapper om = new ObjectMapper();
    Address address = new Address(1,"1000N St", "Fairfield", "Iowa", "USA", "52557");

    LocalDate birthDay = LocalDate.of(1993, Month.JULY, 12);


    Person person = new Person(1, "Mickias", "Mekonnen", "Hailemariam", birthDay, "mike", "M", "+251911636363", "mickias6@gmail.com",address);

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(personController)
                .build();
    }

    @Test
    public void getPerson() throws Exception {
        MvcResult result = mockMvc.perform(get(PersonController.BASE_URL).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int content = result.getResponse().getStatus();
        assertEquals(content, HttpStatus.FORBIDDEN.value());
    }
        @Test
        public void getPersonById ()throws Exception {
            when(personService.getPersonById(1)).thenReturn(person);
            mockMvc.perform(get(PersonController.BASE_URL + "/1").contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.personID", is(1)))
                    .andExpect(jsonPath("$.firstName", is("Mickias")))
                    .andExpect(jsonPath("$.middleName", is("Mekonnen")))
                    .andExpect(jsonPath("$.lastName", is("Hailemariam")))
                    //.andExpect(jsonPath("$.birthDate", is(birthDay)))
                    .andExpect(jsonPath("$.userName", is("mike")))
                    .andExpect(jsonPath("$.gender", is("M")))
                    .andExpect(jsonPath("$.phoneNumber", is("+251911636363")))
                    .andExpect(jsonPath("$.email", is("mickias6@gmail.com")))
                    //.andExpect(jsonPath("$.address", is(address)))
                    .andReturn();

            verify(personService, times(1)).getPersonById(1);
            verifyNoMoreInteractions(personService);
        }

     /*   @Test
        public void saveNewPerson () throws Exception {
            String jsonRequest = om.writeValueAsString(person);
            when(personService.savePerson(person)).thenReturn(person);
            MvcResult result = mockMvc.perform(post(PersonController.BASE_URL +"/").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.personID", is(1)))
                    .andExpect(jsonPath("$.firstName", is("Mickias")))
                    .andExpect(jsonPath("$.middleName", is("Mekonnen")))
                    .andExpect(jsonPath("$.lastName", is("Hailemariam")))
                    .andExpect(jsonPath("$.birthDate", is(birthDay)))
                    .andExpect(jsonPath("$.userName", is("mike")))
                    .andExpect(jsonPath("$.gender", is("M")))
                    .andExpect(jsonPath("$.phoneNumber", is("+251911636363")))
                    .andExpect(jsonPath("$.email", is("mickias6@gmail.com")))
                   . andExpect(jsonPath("$.address", is(address)))
                    .andReturn();

            String content = result.getResponse().getContentAsString();
            assertEquals(content, jsonRequest);

        }*/
    }







