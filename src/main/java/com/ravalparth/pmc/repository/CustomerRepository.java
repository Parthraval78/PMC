package com.ravalparth.pmc.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ravalparth.pmc.model.Customer;
import com.ravalparth.pmc.model.FileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Repository
public class CustomerRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerRepository.class);
    private final DynamoDBMapper dynamoDBMapper;
    public CustomerRepository(@Autowired final DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }


    public String saveCustomer(final MultipartFile multipartFile, final String perfectCustomer) throws IOException {
        var customerObject = new ObjectMapper().readValue(perfectCustomer, Customer.class);
        customerObject.setBill(multipartFile.getBytes());
        customerObject.setFileName(multipartFile.getOriginalFilename());
        dynamoDBMapper.save(customerObject);
        return "success";
    }

    public Customer getCustomerByInvoiceNo(final String invoiceNo) {
        return dynamoDBMapper.load(Customer.class, invoiceNo);
    }

    public FileResponse getCustomerBillByInvoiceNo(final String invoiceNo) {
        var response = dynamoDBMapper.load(Customer.class, invoiceNo);
        var bytes = response.getBill();
        return new FileResponse(new ByteArrayResource(bytes), response.getFileName(), bytes.length);
    }
}
