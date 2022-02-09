package com.oms.projectbuddy.repository;

import io.micronaut.data.jpa.repository.JpaRepository;

import com.oms.projectbuddy.model.Country;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country,Long>{

    Country findByCountryName(String countryName);

    List<Country> findByRegion(String region);


    List<Country> findAllByOrderBySequenceIdDesc();
}
