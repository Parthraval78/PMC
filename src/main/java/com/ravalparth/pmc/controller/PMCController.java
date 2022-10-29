package com.ravalparth.pmc.controller;

import com.ravalparth.pmc.model.Customer;
import com.ravalparth.pmc.service.PMCService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/pmc/customer")
public class PMCController {

    private final PMCService pmcService;

    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> addCustomer(@RequestBody @Validated final Customer customer) {
        var customer1 = pmcService.addCustomer(customer);
        return new ResponseEntity<>(customer1, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomerDetails(@PathVariable("id") final long id) {
        return new ResponseEntity<>(pmcService.getCustomerById(id), HttpStatus.OK);
    }
}
