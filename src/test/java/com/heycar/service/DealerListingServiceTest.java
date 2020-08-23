package com.heycar.service;


import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.heycar.mapper.ListingMapper;
import com.heycar.model.CarListing;
import com.heycar.model.DealerListing;
import com.heycar.repository.CarListingRepository;
import com.heycar.repository.DealerListingRepository;


@RunWith( MockitoJUnitRunner.class )
public class DealerListingServiceTest {

    @Mock
    private ListingMapper mapper;

    @Mock
    private DealerListingRepository dealerListingrepository;

    @Mock
    private CarListingRepository carListingrepository;

    @InjectMocks
    private DealerListingService dealerListingService;

    @Captor
    private ArgumentCaptor<CarListing> carListingCaptor;

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
    private static final Long DEALER_ID = 1l;

    @Test
    private void createOrUpdateListing_listNotPresent_created() {

        when( dealerListingrepository.findByDealerId( DEALER_ID ) ).thenReturn( Optional.of( getDealerListing() ) );
        when( carListingrepository.findByCodeAndDealerId( CODE, DEALER_ID ) ).thenReturn( Optional.of( getCarListing() ) );

        dealerListingService.createOrUpdateListing( getCarListingList(), DEALER_ID );

        verify( dealerListingrepository ).findByDealerId( DEALER_ID );
        verify( carListingrepository ).findByCodeAndDealerId( CODE, DEALER_ID );
        verify( dealerListingrepository ).save( Mockito.any() );
        verify( carListingrepository ).save( carListingCaptor.capture() );

        assertEquals( carListingCaptor.getValue().getColor(), getCarListing().getColor() );

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

    private DealerListing getDealerListing() {

        DealerListing dealerListing = new DealerListing();
        dealerListing.setId( 1l );
        dealerListing.setId( 1l );
        return dealerListing;

    }

}
