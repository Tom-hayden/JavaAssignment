package com.example.h2Database.controller;

import com.example.h2Database.model.Customer;
import com.example.h2Database.repositories.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;


@RestController
public class CustomerController {

    private final CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/customer")
    public ResponseEntity<Customer> getCustomer(@RequestParam UUID id) {
        Optional<Customer> a = repository.findById(id);

        return a.map(customer ->
                new ResponseEntity<>(customer, HttpStatus.OK)
                ).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping("/customer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {

        Customer savedCustomer = repository.save(customer);

        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

}
