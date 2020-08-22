package com.heycar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.heycar.model.CarListing;

@Repository
public interface CarListingRepository extends JpaRepository<CarListing, Long> {
}
