package com.heycar.service;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.heycar.dto.CarListingCSVDTO;
import com.heycar.model.CarListing;
import com.heycar.model.DealerListing;
import com.heycar.repository.CarListingRepository;
import com.heycar.repository.DealerListingRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ListingService {

    private final CarListingRepository carListingrepository;

    private final DealerListingRepository dealerListingRepository;

    public void parseCsvFile( MultipartFile file ) {
        try( Reader reader = new BufferedReader( new InputStreamReader( file.getInputStream() ) ) ) {

            // create csv bean reader
            CsvToBean<CarListingCSVDTO> csvToBean = new CsvToBeanBuilder( reader )
                    .withType( CarListingCSVDTO.class )
                    .withIgnoreLeadingWhiteSpace( true )
                    .build();

            // convert `CsvToBean` object to list of users
            List<CarListingCSVDTO> users = csvToBean.parse();

            // TODO: save users in DB?

        }
        catch( Exception ex ) {

        }
    }

    public void createOrUpdateListing( final List<CarListing> list, Long dealerId ) {

        DealerListing dealerListing = dealerListingRepository.findByDealerId( dealerId )
                .orElse( DealerListing.builder().dealerId( dealerId ).build() );

        createDealistingListingIfNotExist( dealerListing );

        list.stream().forEach( car -> {

            CarListing carListing = carListingrepository.findByCodeAndDealerId( car.getCode(), car.getDealerListing().getDealerId() )
                    .orElse( CarListing.builder().build() );
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
