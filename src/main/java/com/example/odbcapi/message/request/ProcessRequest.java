package com.example.odbcapi.message.request;

import com.example.odbcapi.message.Param;
import com.example.odbcapi.value.Behavior;
import com.example.odbcapi.value.SourceType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProcessRequest {

    @JsonProperty("source_type")
    private SourceType sourceType;

    @JsonProperty("behavior")
    private Behavior behavior;

    @JsonProperty("param")
    private Param param;

}
