package com.heycar.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.heycar.dto.CarListingDTO;
import com.heycar.dto.CarListingsDTO;
import com.heycar.mapper.ListingMapper;
import com.heycar.model.CarListing;
import com.heycar.repository.CarListingRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CarListingService {

    private final CarListingRepository carListingrepository;

    private final ListingMapper mapper;

    public CarListingsDTO getListing( String color, String make, String model, Integer year ) {
        
        List<CarListingDTO> carListingDTO;
        CarListingsDTO dto = new CarListingsDTO();
        List<CarListing> carListing = carListingrepository.findByColorAndMakeAndModelAndYear( color, make, model, year );
        
        if( !carListing.isEmpty() ) {
            carListingDTO = carListing.stream().map( mapper::mapToDTO ).collect( Collectors.toList() );
            dto.setCarListingList( carListingDTO );
        }

        return dto;
    }

}
