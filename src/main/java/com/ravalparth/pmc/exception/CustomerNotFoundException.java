package com.ravalparth.pmc.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(final String message) {
        super("Customer not found for input " + message);
    }
}
