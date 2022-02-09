package com.oms.projectbuddy.repository;

import java.util.List;

import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;

import com.oms.projectbuddy.model.City;

@Repository
public interface CityRepository extends JpaRepository<City,Long>{

	@Query(value = "SELECT * FROM city where state_id=? group by city_name;", nativeQuery = true)
	List<City> findByStateId(Long stateId);

}
