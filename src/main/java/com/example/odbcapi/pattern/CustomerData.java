package com.example.odbcapi.pattern;

import com.example.odbcapi.model.customer.Customer;
import com.example.odbcapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerData implements Source {

    @Autowired
    private CustomerService customerService;

    @Override
    public List<Customer> getAllData() {
        return customerService.getAll();
    }
}
