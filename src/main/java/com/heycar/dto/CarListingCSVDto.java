package com.heycar.dto;


import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
public class CarListingCSVDTO {

    @CsvBindByName
    private String code;

    @CsvBindByName
    private String make;

    @CsvBindByName
    private String model;

    @CsvBindByName
    private int powerInPs;

    @CsvBindByName
    private int year;

    @CsvBindByName
    private String color;

    @CsvBindByName
    private double price;

}
