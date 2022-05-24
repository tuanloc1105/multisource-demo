package com.example.odbcapi.pattern;

import com.example.odbcapi.SourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataFactory {

    @Autowired
    private CustomerData customerData;

    @Autowired
    private OrderData orderData;

    public Source getSource(SourceType type) throws Exception {
        switch (type) {
            case ORDER:
                return orderData;
            case CUSTOMER:
                return customerData;
            default:
                throw new Exception("Unknown source");
        }
    }
}
