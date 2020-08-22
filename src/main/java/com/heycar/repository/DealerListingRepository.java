package com.heycar.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.heycar.model.DealerListing;


@Repository
public interface DealerListingRepository extends JpaRepository<DealerListing, Long> {
}