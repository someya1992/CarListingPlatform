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
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.heycar.CarListingPlatformApplication;
import com.heycar.dto.CarListingDTO;
import com.heycar.dto.CarListingsDTO;
import com.heycar.repository.CarListingRepository;


@SpringBootTest( classes = CarListingPlatformApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT )
public class DealerListingControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    CarListingRepository carListingRepository;

    private static final String MAKE = "Renault";
    private static final String MODEL = "a-65";
    private static final String COLOR = "Black";
    private static final String CODE = "a";
    private static final int KW = 200;
    private static final int YEAR = 2016;
    private static final double PRICE = 12000;

    @Sql( { "schema.sql" } )
    @Test
    public void addVehicleListing() {

        ResponseEntity<String> response = restTemplate
                .postForEntity( "http://localhost:" + port + "/vehicleListings/1", getCarListingsDTO(), String.class );

        assertEquals( response.getStatusCode(), HttpStatus.OK );

        assertEquals( carListingRepository.count(), 1 );

    }

    // @Sql( { "schema.sql", "data.sql" } )
    @Test
    @Ignore
    public void uploadVehicleListing() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.MULTIPART_FORM_DATA );

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        ContentDisposition contentDisposition = ContentDisposition
                .builder( "form-data" )
                .name( "file" )
                .filename( "cars.csv" )
                .build();
        body.add( HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString() );

        HttpEntity<MultiValueMap<String, Object>> requestEntity
                                                                = new HttpEntity<>( body, headers );

        ResponseEntity<String> response = restTemplate
                .postForEntity( "http://localhost:" + port + "/upload_csv/1", requestEntity, String.class );

        assertEquals( response.getStatusCode(), HttpStatus.OK );

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
