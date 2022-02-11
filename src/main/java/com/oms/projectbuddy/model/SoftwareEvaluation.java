package com.oms.projectbuddy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;


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

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public long getWeight() {
		return weight;
	}

	public void setWeight(long weight) {
		this.weight = weight;
	}

	public long getCustomerRating() {
		return customerRating;
	}

	public void setCustomerRating(long customerRating) {
		this.customerRating = customerRating;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public Long getCriteriaId() {
		return criteriaId;
	}

	public void setCriteriaId(Long criteriaId) {
		this.criteriaId = criteriaId;
	}
}


