package com.heycar.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.heycar.dto.CarListingsDTO;
import com.heycar.service.CarListingService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class CarListingController {

    private final CarListingService service;

    @GetMapping( path = "/search" )
    public ResponseEntity<CarListingsDTO> getListing( @RequestParam String color,
                                                      @RequestParam String make,
                                                      @RequestParam String model,
                                                      @RequestParam int year ) {

        CarListingsDTO carListing = service.getListing( color, make, model, year );

        return ResponseEntity.ok().body( carListing );
    }

}
