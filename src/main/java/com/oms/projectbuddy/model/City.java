package com.oms.projectbuddy.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "city")
public class City {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    @Column(name = "city_name")
	    private String cityName;
	    @Column(name = "state_id")
	    private Long stateId;
	

}
