package com.heycar.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.heycar.dto.CarListingsDTO;
import com.heycar.exceptions.FileEmptyException;
import com.heycar.mapper.DTOToEntityMapper;
import com.heycar.model.CarListing;
import com.heycar.service.ListingService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class ListingController {

    private final ListingService service;

    private final DTOToEntityMapper mapper;

    @PostMapping( value = "/upload_csv/{dealerId}", consumes = "test/csv" )
    public ResponseEntity<String> uploadListing( @RequestParam( "file" ) MultipartFile file,
                                                 @PathVariable String dealerId ) throws FileEmptyException {
        if( file.isEmpty() ) {
            throw new FileEmptyException( "File is empty.Please add listing data" );
        }

        service.parseCsvFile( file );

        return new ResponseEntity<String>( HttpStatus.OK );
    }

    @PostMapping( value = "/vehicleListings/{dealerId}", consumes = "application/json" )
    public ResponseEntity<String> addCarListing( @RequestBody CarListingsDTO carListing, @PathVariable String dealerId ) {

        List<CarListing> list = carListing.getCarListingList().stream().map( mapper::mapToEntity ).collect( Collectors.toList() );

        service.createOrUpdateListing( list, Long.valueOf( dealerId ) );

        return new ResponseEntity<String>( HttpStatus.OK );

    }

}
