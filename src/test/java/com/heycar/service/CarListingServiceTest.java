package com.heycar.service;


import static java.util.Collections.EMPTY_LIST;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.heycar.dto.CarListingDTO;
import com.heycar.dto.CarListingsDTO;
import com.heycar.mapper.ListingMapper;
import com.heycar.model.CarListing;
import com.heycar.model.DealerListing;
import com.heycar.repository.CarListingRepository;


@RunWith( MockitoJUnitRunner.class )
public class CarListingServiceTest {

    @Mock
    private ListingMapper mapper;

    @Mock
    private CarListingRepository carListingrepository;

    @InjectMocks
    private CarListingService listingService;

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

    @Test
    public void getListing_listingPresent() {

        when( carListingrepository.findByColorAndMakeAndModelAndYear( COLOR, MAKE, MODEL, YEAR ) ).thenReturn( getCarListingList() );
        Mockito.when( mapper.mapToDTO( getCarListing() ) ).thenReturn( getCarListingdto() );

        CarListingsDTO listings = listingService.getListing( COLOR, MAKE, MODEL, YEAR );

        assertEquals( listings.getCarListingList().size(), 1 );
        verify( carListingrepository ).findByColorAndMakeAndModelAndYear( COLOR, MAKE, MODEL, YEAR );
        verify( mapper ).mapToDTO( Mockito.any() );

    }

    @Test
    public void getListing_ListingNotExist() {

        when( carListingrepository.findByColorAndMakeAndModelAndYear( COLOR, MAKE, MODEL, YEAR ) ).thenReturn( EMPTY_LIST );

        CarListingsDTO listings = listingService.getListing( COLOR, MAKE, MODEL, YEAR );

        assertEquals( listings.getCarListingList(), null );
        verify( carListingrepository ).findByColorAndMakeAndModelAndYear( COLOR, MAKE, MODEL, YEAR );
        verify( mapper, never() ).mapToDTO( Mockito.any() );

    }

    private CarListingDTO getCarListingdto() {

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
