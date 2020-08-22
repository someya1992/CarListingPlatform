package com.heycar.model;


import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "dealer_listing")
public class DealerListing {
    
    @Id
    @GeneratedValue( strategy = IDENTITY )
    @Column( name = "id" )
    private Long id;
    

}
