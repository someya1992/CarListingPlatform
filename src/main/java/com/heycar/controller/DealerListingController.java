package com.heycar.controller;


import java.io.IOException;
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
import com.heycar.exceptions.FileParsingException;
import com.heycar.mapper.ListingMapper;
import com.heycar.model.CarListing;
import com.heycar.service.CSVHelper;
import com.heycar.service.DealerListingService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class DealerListingController {

    private final DealerListingService service;

    private final ListingMapper mapper;

    @PostMapping( value = "/upload_csv/{dealerId}" )
    public ResponseEntity<String> uploadListing( @RequestParam( "file" ) MultipartFile file,
                                                 @PathVariable String dealerId ) throws FileParsingException, IOException {

        if( !CSVHelper.hasCSVFormat( file ) ) {
            throw new FileParsingException( "File is empty or invalid format" );
        }
        List<CarListing> carListing = CSVHelper.csvToObject( file.getInputStream() ).stream().map( mapper::mapFromCSVDTO )
                .collect( Collectors.toList() );

        service.createOrUpdateListing( carListing, Long.valueOf( dealerId ) );

        return new ResponseEntity<String>( HttpStatus.OK );
    }

    @PostMapping( path = "/vehicleListings/{dealerId}", consumes = "application/json" )
    public ResponseEntity<String> addListing( @RequestBody CarListingsDTO carListingDTO, @PathVariable String dealerId ) {

        List<CarListing> list = carListingDTO.getCarListingList().stream().map( mapper::mapToEntity ).collect(
                                                                                                               Collectors.toList() );

        service.createOrUpdateListing( list, Long.valueOf( dealerId ) );

        return new ResponseEntity<String>( HttpStatus.OK );

    }

}
