package com.example.odbcapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddCustomerDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("age")
    private Integer age;

}
