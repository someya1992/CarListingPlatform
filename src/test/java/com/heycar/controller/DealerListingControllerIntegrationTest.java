package com.heycar.controller;


import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import com.heycar.CarListingPlatformApplication;
import com.heycar.dto.CarListingDTO;
import com.heycar.dto.CarListingsDTO;


@SpringBootTest( classes = CarListingPlatformApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT )
public class DealerListingControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String MAKE = "Renault";
    private static final String MODEL = "a-65";
    private static final String COLOR = "Black";
    private static final String CODE = "a";
    private static final int KW = 200;
    private static final int YEAR = 2016;
    private static final double PRICE = 12000;

    
  //  @Sql( { "schema.sql", "data.sql" } )
    @Test
    @Ignore
    public void addVehicleListing() {

        assertEquals(  this.restTemplate
                .postForObject( "http://localhost:" + port + "/vehicleListings/1", ( getCarListingsDTO() ), CarListingsDTO.class ) ,
                      null);

    }
    
   // @Sql( { "schema.sql", "data.sql" } )
    @Test
    @Ignore
    public void uploadVehicleListing() {

        assertEquals(  this.restTemplate
                .postForObject( "http://localhost:" + port + "/vehicleListings/1", ( getCarListingsDTO() ), CarListingsDTO.class ) ,
                      null);

    }

    private CarListingsDTO getCarListingsDTO() {

        CarListingsDTO dto = new CarListingsDTO();
        dto.setCarListingList( Collections.singletonList( getCarListingdto() ) );
        return dto;

    }

    private CarListingDTO getCarListingdto() {

        CarListingDTO listing = new CarListingDTO();
        listing.setCode( CODE );
        listing.setColor( COLOR );
        listing.setKW( KW );
        listing.setMake( MAKE );
        listing.setModel( MODEL );
        listing.setPrice( PRICE );
        listing.setYear( YEAR );

        return listing;

    }

}
