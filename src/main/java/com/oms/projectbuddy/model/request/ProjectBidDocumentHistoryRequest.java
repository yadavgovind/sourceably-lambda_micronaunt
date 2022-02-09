package com.oms.projectbuddy.model.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProjectBidDocumentHistoryRequest {

	private Long id;

	private String supplierId;

	private String consumerId;

	private String projectId;

	private String comment;

	private String attachFile;


	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate createdDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate updatedDate;

}
