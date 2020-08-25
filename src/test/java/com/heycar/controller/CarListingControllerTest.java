package com.heycar.controller;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heycar.dto.CarListingDTO;
import com.heycar.dto.CarListingsDTO;
import com.heycar.service.CarListingService;


@ExtendWith( SpringExtension.class )
@WebMvcTest( controllers = CarListingController.class )
public class CarListingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CarListingService listingService;

    @InjectMocks
    private CarListingController controller;

    private static final String MAKE = "Renault";
    private static final String MODEL = "a-65";
    private static final String COLOR = "Black";
    private static final String CODE = "a";
    private static final int KW = 200;
    private static final int YEAR = 2016;
    private static final double PRICE = 12000;

    @Test
    public void getListing_whenInvalidNull_returns400() throws Exception {

        mockMvc.perform( get( "/search" )
                .contentType( "application/json" ) )
                .andExpect( status().is4xxClientError() );
    }

    @Test
    public void getListing_whenValidInput_returns200() throws Exception {

        when( listingService.getListing( COLOR, MAKE, MODEL, YEAR ) ).thenReturn( getCarListingDTO() );

        CarListingsDTO mvcResult = objectMapper.readValue( mockMvc
                .perform( get( "/search" ).queryParam( "color", COLOR ).queryParam( "make", MAKE ).queryParam( "model", MODEL )
                        .queryParam( "year", "2016" )
                        .contentType( "application/json" ) )
                .andExpect( status().isOk() )
                .andReturn().getResponse().getContentAsByteArray(),
                                                           CarListingsDTO.class );

        assertEquals( mvcResult.getCarListingList().size(), getCarListingDTO().getCarListingList().size() );

    }

    private CarListingsDTO getCarListingDTO() {

        CarListingsDTO dto = new CarListingsDTO();
        List<CarListingDTO> list = new LinkedList<>();
        list.add( getCarListing() );
        dto.setCarListingList( list );
        return dto;

    }

    private CarListingDTO getCarListing() {

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
