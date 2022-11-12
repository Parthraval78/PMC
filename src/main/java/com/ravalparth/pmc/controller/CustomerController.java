package com.ravalparth.pmc.controller;

import com.ravalparth.pmc.model.Customer;
import com.ravalparth.pmc.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/eway/perfect")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;


    @PostMapping(path = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String saveCustomer(@RequestPart(value = "bill") final MultipartFile multipartFile,
                               @RequestPart final String customer) throws IOException {
        return customerRepository.saveCustomer(multipartFile, customer);
    }

    @GetMapping("/getDetails/{invoiceNo}")
    public Customer getCustomerByInvoiceNo(@PathVariable("invoiceNo") final String invoiceNo) {
        return customerRepository.getCustomerByInvoiceNo(invoiceNo);
    }

    @GetMapping("/getFile/{invoiceNo}")
    public ResponseEntity<?> getCustomerBillByInvoiceNo(@PathVariable("invoiceNo") final String invoiceNo) throws FileNotFoundException {
        var response = customerRepository.getCustomerBillByInvoiceNo(invoiceNo);

        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + response.getFileName() + ".pdf");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        return ResponseEntity.ok().headers(headers).contentLength(response.getLength()).body(response.getFile());
    }
}
