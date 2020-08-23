package com.heycar.mapper;


import com.heycar.dto.CarListingDTO;
import com.heycar.model.CarListing;


public class DTOToEntityMapper {

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

}
