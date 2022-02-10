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

@Getter
@Setter
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

}
