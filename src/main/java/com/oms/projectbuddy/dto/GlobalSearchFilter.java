package com.oms.projectbuddy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GlobalSearchFilter {

	private String country;

	private String state;

	private String city;

	private Double rating;

	private String category;

	private String subCategory;
	private List<String> proBusCertificateIds;
	private List<String> proCyberCertificateIds;
	private String insRequirement;

}
