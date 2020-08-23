package com.heycar.mapper;


import org.springframework.stereotype.Component;

import com.heycar.dto.CarListingCSVDTO;
import com.heycar.dto.CarListingDTO;
import com.heycar.model.CarListing;


@Component
public class ListingMapper {

    public CarListing mapToEntity( CarListingDTO carListingDTO ) {

        CarListing listing = new CarListing();
        listing.setCode( carListingDTO.getCode() );
        listing.setColor( carListingDTO.getColor() );
        listing.setKW( carListingDTO.getKW() );
        listing.setMake( carListingDTO.getMake() );
        listing.setModel( carListingDTO.getModel() );
        listing.setPrice( carListingDTO.getPrice() );
        listing.setYear( carListingDTO.getYear() );
        return listing;
    }

    public CarListingDTO mapToDTO( CarListing carListing ) {

        CarListingDTO listing = new CarListingDTO();
        listing.setCode( carListing.getCode() );
        listing.setColor( carListing.getColor() );
        listing.setKW( carListing.getKW() );
        listing.setMake( carListing.getMake() );
        listing.setModel( carListing.getModel() );
        listing.setPrice( carListing.getPrice() );
        listing.setYear( listing.getYear() );
        return listing;

    }

    public CarListing mapFromCSVDTO( CarListingCSVDTO carListing ) {

        CarListing listing = new CarListing();
        listing.setCode( carListing.getCode() );
        listing.setColor( carListing.getColor() );
        listing.setKW( carListing.getPowerInPs() );
        listing.setMake( carListing.getMake() );
        listing.setModel( carListing.getModel() );
        listing.setPrice( carListing.getPrice() );
        listing.setYear( listing.getYear() );
        return listing;

    }

}
