package com.example.odbcapi.pattern;

import com.example.odbcapi.model.order.Order;
import com.example.odbcapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderData implements Source {

    @Autowired
    private OrderService orderService;

    @Override
    public List<Order> getAllData() {
        return orderService.getAll();
    }
}
