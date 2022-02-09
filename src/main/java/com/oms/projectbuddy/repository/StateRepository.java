package com.oms.projectbuddy.repository;

import java.util.List;

import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.annotation.Query;
import com.oms.projectbuddy.model.State;

public interface StateRepository extends JpaRepository<State,Long>{
	@Query(value = "SELECT * FROM  state where Country_id=? group by state_name;", nativeQuery = true)
	List<State> findByCountryId(Long stateId);

}
