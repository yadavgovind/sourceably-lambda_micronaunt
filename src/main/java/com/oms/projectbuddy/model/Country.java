package com.oms.projectbuddy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "country")
public class Country {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    @Column(name="country_name")
	    private String countryName;
	    @Column(name="phone_code")
	    private String phoneCode;
	    @Column(name="short_name")
	    private String shortName;
	    @Column(name="region")
	    private String region;
		@Column(name="sequence_id")
		private Integer sequenceId;

	    
	}


