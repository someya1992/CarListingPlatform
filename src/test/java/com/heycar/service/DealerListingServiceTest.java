package com.heycar.service;


import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.heycar.mapper.ListingMapper;
import com.heycar.model.CarListing;
import com.heycar.model.DealerListing;
import com.heycar.repository.DealerListingRepository;


@RunWith( MockitoJUnitRunner.class )
public class DealerListingServiceTest {

    @Mock
    private ListingMapper mapper;

    @Mock
    private DealerListingRepository dealerListingrepository;

    @InjectMocks
    private DealerListingService dealerListingService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks( this );
    }

    private static final String MAKE = "Renault";
    private static final String MODEL = "a-65";
    private static final String COLOR = "Black";
    private static final String CODE = "a";
    private static final int KW = 200;
    private static final int YEAR = 2016;
    private static final double PRICE = 12000;

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
