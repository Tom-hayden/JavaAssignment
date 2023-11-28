package com.example.h2Database.controller;

import com.example.h2Database.model.Customer;
import com.example.h2Database.repositories.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CustomerControllerTest {

    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CustomerController controller;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void GetCustomer_CustomerExists() throws Exception {
        UUID customerId = UUID.fromString("5fc03087-d265-11e7-b8c6-83e29cd24f4c");
        Customer customer = new Customer(
                customerId,
                "customerName",
                "addressLine1",
                "addressLine2",
                "town",
                "county",
                "country",
                "postcode");

        when(repository.findById(customerId)).thenReturn(Optional.of(customer));


        mockMvc.perform(MockMvcRequestBuilders.get("/customer")
                        .param("id", customerId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

        verify(repository).findById(UUID.fromString("5fc03087-d265-11e7-b8c6-83e29cd24f4c"));
    }

    @Test
    public void GetCustomer_CustomerDoesNotExists() throws Exception {
        UUID customerId = UUID.fromString("5fc03087-d265-11e7-b8c6-83e29cd24f4c");

        when(repository.findById(customerId)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/customer")
                        .param("id", customerId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound());

        verify(repository).findById(UUID.fromString("5fc03087-d265-11e7-b8c6-83e29cd24f4c"));
    }

    @Test
    public void AddCustomer() throws Exception {
        UUID customerRef = UUID.fromString("5fc03087-d265-11e7-b8c6-83e29cd24f4c");
        Customer customer = new Customer(
                customerRef,
                "customerName",
                "addressLine1",
                "addressLine2",
                "town",
                "county",
                "country",
                "postcode");

        ObjectMapper objectMapper = new ObjectMapper();
        String customerJson = objectMapper.writeValueAsString(customer);

        when(repository.save(any(Customer.class))).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.post("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isCreated());

        verify(repository).save(customer);

    }
}