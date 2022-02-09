package com.oms.projectbuddy.services;

import java.util.List;

import com.oms.projectbuddy.model.City;
import com.oms.projectbuddy.model.Country;
import com.oms.projectbuddy.model.State;



public interface IAddressService {
    List<Country> getAllCountry();
	
	//List<Country> getCountry();

    List<State> getStateByCountry(Long countryId) ;

    List<City> getCityByStateId(Long stateId) ;

    List<Country> getCountryByRegion(String region) ;

}
