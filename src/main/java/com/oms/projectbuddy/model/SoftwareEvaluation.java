package com.oms.projectbuddy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sbly_software_evaluation")
public class SoftwareEvaluation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "supplier_Id")
	private String supplierId;

	@Column(name = "weight")
	private long weight;

	@Column(name = "customer_rating")
	private long customerRating;

	@Column(name = "score")
	private double score;

	@Column(name = "criteria_id")
	private Long criteriaId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}


