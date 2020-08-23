package com.heycar.model;


import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car_listing")
public class CarListing {

    @Id
    @GeneratedValue( strategy = IDENTITY )
    @Column( name = "id" )
    private Long id;

    @Column( name = "code" )
    private String code;

    @Column( name = "make" )
    private String make;

    @Column( name = "model" )
    private String model;

    @Column( name = "kW" )
    private int kW;

    @Column( name = "year" )
    private int year;

    @Column( name = "color" )
    private String color;

    @Column( name = "price" )
    private double price;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dealerId", nullable = false)
    private DealerListing dealerListing;

}
