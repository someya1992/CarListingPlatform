package com.heycar.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.heycar.exceptions.FileEmptyException;
import com.heycar.service.ListingService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class ListingController {

    private final ListingService service;

    @PostMapping( "/upload_csv/{dealerId}" )
    public void uploadListing( @RequestParam( "file" ) MultipartFile file,
                               @PathVariable String dealerId ) throws FileEmptyException {
        if( file.isEmpty() )
            throw new FileEmptyException( "File is empty.Please add listing data" );

        service.parseCsvFile( file );

    }

}
