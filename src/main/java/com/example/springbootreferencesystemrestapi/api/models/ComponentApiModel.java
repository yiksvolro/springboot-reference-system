package com.example.springbootreferencesystemrestapi.api.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonAutoDetect
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComponentApiModel {
    @JsonProperty("id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int id;
    @JsonProperty("type")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String type;
    @JsonProperty("brand")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String brand;
    @JsonProperty("inventoryNumber")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String inventoryNumber;
    @JsonProperty("computerId")
    private int computerId;
    @JsonProperty("computer")
    private ComputerApiModel computer;
    public ComponentApiModel(){

    }
}
