package com.example.springbootreferencesystemrestapi.api.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonAutoDetect
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComputerApiModel {
    @JsonProperty("id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int id;
    @JsonProperty("name")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonProperty("description")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;
    @JsonProperty("inventoryNumber")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String inventoryNumber;

    public ComputerApiModel(){

    }
}
