package com.oms.projectbuddy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.oms.projectbuddy.dto.ProjectMilestoneListResponse;
import com.oms.projectbuddy.utils.StatusEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "sbly_project_milestone_list")
public class ProjectMileStoneList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "milestone_id")
	private Long milestoneId;
	@Column(name = "project_id")
	private String projectId;

	@Column(name = "milestone_type")
	@Enumerated(EnumType.STRING)
	private StatusEnum milestoneType;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@Column(name = "date")
	//@ApiModelProperty(value = "Date in yyyy-MM-dd format")
	private LocalDate date;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@Column(name = "created_epoch_time")
	private LocalDate createdDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@Column(name = "updated_epoch_time")
	private LocalDate updatedDate;

	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "updatedBy")
	private String updatedBy;

	public ProjectMilestoneListResponse convertToResponse() {

		return ProjectMilestoneListResponse.builder().id(id).milestoneId(milestoneId).date(date).projectId(projectId)
				.createdDate(createdDate).updatedDate(updatedDate).build();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMilestoneId() {
		return milestoneId;
	}

	public void setMilestoneId(Long milestoneId) {
		this.milestoneId = milestoneId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public StatusEnum getMilestoneType() {
		return milestoneType;
	}

	public void setMilestoneType(StatusEnum milestoneType) {
		this.milestoneType = milestoneType;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
}
