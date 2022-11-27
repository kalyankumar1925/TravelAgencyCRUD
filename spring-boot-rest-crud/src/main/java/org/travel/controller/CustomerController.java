package org.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.travel.entity.Customer;
import org.travel.exception.RecordNotFoundException;
import org.travel.exception.Validation;
import org.travel.service.CustomerServiceImpl;

@RestController
@RequestMapping("/travelAgency")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl service;

    @PostMapping("/save")
    public ResponseEntity<String> saveCustomer(@RequestBody Customer c) throws Validation {
        return new ResponseEntity<String>(service.saveCustomer(c),HttpStatus.CREATED);
    }


    @GetMapping("/getAll")
    public Iterable<Customer> getAllCustomers(){
        return service.getAllCustomers();
    }

    @GetMapping("getData/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") long id) throws RecordNotFoundException {
        return new ResponseEntity<Customer>(service.findCustomerById(id),HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateCustomer(@RequestBody Customer c,@PathVariable("id") long id) throws RecordNotFoundException, Validation {
        return new ResponseEntity<String>(service.updateCustomer(c, id), HttpStatus.CREATED);

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") long id) throws RecordNotFoundException {
        return new ResponseEntity<String>(service.deleteCustomer(id),HttpStatus.ACCEPTED);
    }
}