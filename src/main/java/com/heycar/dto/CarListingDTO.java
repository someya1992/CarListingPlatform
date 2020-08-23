package com.heycar.dto;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CarListingDTO {

    private String code;

    private String make;

    private String model;

    private int kW;

    private int year;

    private String color;

    private double price;
}
