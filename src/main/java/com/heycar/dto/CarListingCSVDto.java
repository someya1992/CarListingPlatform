package com.heycar.dto;


import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CarListingCSVDTO {

   
    private String code;

    
    private String make;

   
    private String model;


    private int powerInPs;

    private int year;


    private String color;


    private double price;

}
