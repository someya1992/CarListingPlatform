package com.heycar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.heycar.model.CarListing;


@Repository
public interface CarListingRepository extends JpaRepository<CarListing, Long> {

    @Query( "SELECT * FROM CarListing c WHERE c.code = :code and c.dealerListing.dealerId = :dealerId " )
    Optional<CarListing> findByCodeAndDealerId( String code, Long dealerId );
}
