package com.example.odbcapi.message.response;

import com.example.odbcapi.value.ProcessStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProcessResponse {

    @JsonProperty("status")
    private ProcessStatus status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private Object data;

}
