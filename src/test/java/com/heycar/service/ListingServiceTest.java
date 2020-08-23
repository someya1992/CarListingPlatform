package com.heycar.service;


import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.heycar.model.CarListing;
import com.heycar.model.DealerListing;
import com.heycar.repository.CarListingRepository;
import com.heycar.repository.DealerListingRepository;


public class ListingServiceTest {

    @Mock
    private DealerListingRepository dealerRepository;

    @Mock
    private CarListingRepository carRepository;

    @InjectMocks
    private ListingService listingService;

    private static final String MAKE = "Renault";
    private static final String MODEL = "a-65";
    private static final String COLOR = "Black";
    private static final String CODE = "a";
    private static final int KW = 200;
    private static final int YEAR = 2016;
    private static final double PRICE = 12000;

    public void createOrUpdateListing_ListingNotExist_createListing() {
        
        

    }

    private List<CarListing> getCarListingList() {

        List<CarListing> list = new LinkedList<>();
        list.add( getCarListing() );
        return list;

    }

    private CarListing getCarListing() {
        DealerListing dealerListing = new DealerListing();
        dealerListing.setDealerId( 1l );

        CarListing listing = new CarListing();
        listing.setCode( CODE );
        listing.setColor( COLOR );
        listing.setKW( KW );
        listing.setMake( MAKE );
        listing.setModel( MODEL );
        listing.setPrice( PRICE );
        listing.setYear( YEAR );
        listing.setDealerListing( dealerListing );

        return listing;

    }

}
