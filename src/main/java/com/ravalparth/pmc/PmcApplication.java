package com.ravalparth.pmc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class PmcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PmcApplication.class, args);
    }

}
