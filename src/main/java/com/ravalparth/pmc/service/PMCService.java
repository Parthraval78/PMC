package com.ravalparth.pmc.service;

import com.ravalparth.pmc.model.Customer;
import com.ravalparth.pmc.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class PMCService {

    private final CustomerRepository customerRepository;

    public PMCService(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer(final Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(final long id) {
        return customerRepository.findCustomerById(id).orElse(null);
    }
}
