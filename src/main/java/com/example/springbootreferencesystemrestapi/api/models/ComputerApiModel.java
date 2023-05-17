package com.example.springbootreferencesystemrestapi.api.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonAutoDetect
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComputerApiModel {
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
    public ComputerApiModel(String name, String description, String inventoryNumber){
        this.name = name;
        this.description = description;
        this.inventoryNumber = inventoryNumber;
    }
}
