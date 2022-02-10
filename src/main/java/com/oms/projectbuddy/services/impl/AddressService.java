package com.oms.projectbuddy.services.impl;

import com.oms.projectbuddy.model.City;
import com.oms.projectbuddy.model.Country;
import com.oms.projectbuddy.model.State;
import com.oms.projectbuddy.repository.CityRepository;
import com.oms.projectbuddy.repository.CountryRepository;
import com.oms.projectbuddy.repository.StateRepository;
import com.oms.projectbuddy.services.IAddressService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class AddressService implements IAddressService {
	@Inject
	private CountryRepository countryRepository;
	@Inject
	private StateRepository stateRepository;
	@Inject
	private CityRepository cityRepository;

	@Override
	public List<Country> getAllCountry() {
		return countryRepository.findAllByOrderBySequenceIdDesc();
	}

	/*@Override
	public List<Country> getCountry() {
		Long id = 231L;
		Country country = countryRepository.findById(id).get();
		System.out.println(country.getCountryName());
		List<Country> countryList = new ArrayList<Country>();
		countryList.add(country);
		return countryList;

	}*/

	@Override
	public List<City> getCityByStateId(Long stateId)  {
		

		return cityRepository.findByStateId(stateId);
		
		}

	@Override
	public List<Country> getCountryByRegion(String region)  {

		return countryRepository.findByRegion(region);
	}

	@Override
	public List<State> getStateByCountry(Long countryId)  {
		//Long countryId = 231L;

		return stateRepository.findByCountryId(countryId);
	}

}
