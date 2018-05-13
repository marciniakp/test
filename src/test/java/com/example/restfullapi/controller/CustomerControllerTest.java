package com.example.restfullapi.controller;

import com.example.restfullapi.api.v1.model.CustomerDTO;
import com.example.restfullapi.services.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {

    @Mock
    CustomerService customerService;
    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void getAllCustomers() throws Exception {
    /*    CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstName("pawel");
        customer1.setLastName("wronski");
        customer1.setCustomerURL(CustomerController.BASE_URL + "/1");
        CustomerDTO customer2 = new CustomerDTO();
        customer2.setFirstName("przemek");
        customer2.setLastName("marciniak");
        customer2.setCustomerURL(CustomerController.BASE_URL + "/2");
        CustomerDTO customer3 = new CustomerDTO();
        customer3.setFirstName("kamil");
        customer3.setLastName("kedzierski");
        customer3.setCustomerURL(CustomerController.BASE_URL + "/3");

        List<CustomerDTO> customers = Arrays.asList(customer1,customer2,customer3);

        when(customerService.getAllCustomers()).thenReturn(customers);
*/
        mockMvc.perform(get(CustomerController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers",hasSize(3)));

    }

    @Test
    public void getCustomerById() throws Exception {
    }

    @Test
    public void createNewCustomer() throws Exception {
       /* CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("pawel");
        customerDTO.setLastName("wronski");

        CustomerDTO savedCustomerDTO = new CustomerDTO();
        savedCustomerDTO.setFirstName(customerDTO.getFirstName());
        savedCustomerDTO.setLastName(customerDTO.getLastName());
        savedCustomerDTO.setCustomerURL(CustomerController.BASE_URL + "/1");


        when(customerService.createNewCustomer(customerDTO)).thenReturn(savedCustomerDTO);

        System.out.println(asJsonString(customerDTO));
        mockMvc.perform(post(CustomerController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName",equalTo("pawel")))
                .andExpect(jsonPath("$.customer_url",equalTo(savedCustomerDTO.getCustomerURL())));

*/
    }

    private String asJsonString(CustomerDTO customerDTO) {
        try {
            return new ObjectMapper().writeValueAsString(customerDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Test
    public void deleteCustomer() throws Exception {
    }

}