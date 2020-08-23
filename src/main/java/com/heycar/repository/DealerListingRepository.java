package com.heycar.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.heycar.model.DealerListing;


@Repository
public interface DealerListingRepository extends JpaRepository<DealerListing, Long> {
    
    Optional<DealerListing> findByDealerId( Long dealerId );
}