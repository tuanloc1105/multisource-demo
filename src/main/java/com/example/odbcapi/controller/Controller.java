package com.example.odbcapi.controller;

import com.example.odbcapi.SourceType;
import com.example.odbcapi.dto.AddCustomerDto;
import com.example.odbcapi.dto.AddOrderDto;
import com.example.odbcapi.model.customer.Customer;
import com.example.odbcapi.model.order.Order;
import com.example.odbcapi.pattern.DataFactory;
import com.example.odbcapi.service.CustomerService;
import com.example.odbcapi.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class Controller extends BaseController {

    private final CustomerService customerService;
    private final OrderService orderService;
    private final DataFactory dataFactory;

    @PostMapping("/customer/add")
    public Customer addCustomer(@RequestBody AddCustomerDto request,
                                @RequestHeader(name = "Authorization") String token) throws Exception {
        this.validate(token);
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setAge(request.getAge());
        return customerService.addNewCustomer(customer);
    }

    @PostMapping("/order/add")
    public Order addOrder(@RequestBody AddOrderDto request,
                          @RequestHeader(name = "Authorization") String token) throws Exception {
        this.validate(token);
        Order order = new Order();
        order.setName(request.getName());
        order.setQuality(request.getQuality());
        return orderService.addNewOrder(order);
    }

    @GetMapping("/get-data/{source}")
    public Object getData(@PathVariable(name = "source") SourceType sourceType,
                          @RequestHeader(name = "Authorization") String token) throws Exception {
        this.validate(token);
        return dataFactory.getSource(sourceType).getAllData();
    }

    @PostMapping("/authen")
    public String auth(@RequestParam(name = "password") String password) {
        return this.genToken(password);
    }

}
