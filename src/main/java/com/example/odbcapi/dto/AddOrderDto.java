package com.example.odbcapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddOrderDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("quality")
    private Integer quality;
}
