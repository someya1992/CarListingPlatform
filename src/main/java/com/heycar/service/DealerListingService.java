package com.heycar.service;


import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.heycar.model.CarListing;
import com.heycar.model.DealerListing;
import com.heycar.repository.CarListingRepository;
import com.heycar.repository.DealerListingRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class DealerListingService {

    private final CarListingRepository carListingrepository;

    private final DealerListingRepository dealerListingRepository;

    public void createOrUpdateListing( final List<CarListing> list, Long dealerId ) {

        DealerListing dealerListing = dealerListingRepository.findByDealerId( dealerId )
                .orElse( DealerListing.builder().dealerId( dealerId ).build() );

        createDealistingListingIfNotExist( dealerListing );

        list.stream().forEach( car -> {

            CarListing carListing = carListingrepository.findByCodeAndDealerId( car.getCode(), dealerId ).orElse( new CarListing() );
            carListing.setCode( car.getCode() );
            carListing.setColor( car.getColor() );
            carListing.setDealerListing( dealerListing );
            carListing.setKW( car.getKW() );
            carListing.setMake( car.getMake() );
            carListing.setModel( car.getModel() );
            carListing.setPrice( car.getPrice() );
            carListing.setYear( car.getYear() );
            carListingrepository.save( carListing );
        } );

    }

    private void createDealistingListingIfNotExist( DealerListing dealerListing ) {

        if( Objects.isNull( dealerListing.getId() ) ) {
            dealerListingRepository.save( dealerListing );
        }

    }

}
