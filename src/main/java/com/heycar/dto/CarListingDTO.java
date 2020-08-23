package com.heycar.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CarListingDTO {

    @JsonProperty
    private String code;

    @JsonProperty
    private String make;

    @JsonProperty
    private String model;

    @JsonProperty
    private int kW;

    @JsonProperty
    private int year;

    @JsonProperty
    private String color;

    @JsonProperty
    private double price;
}
