package com.ravalparth.pmc.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "customer")
public class Customer {

    @Id
    private final long id;
    private final String name;
    private final String address;

    private final String gstPin;

    public Customer(long id, String name, String address, String gstPin) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.gstPin = gstPin;
    }
}
