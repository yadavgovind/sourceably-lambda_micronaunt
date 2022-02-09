package com.oms.projectbuddy.utils;

import java.util.Arrays;
import java.util.Optional;

public enum ProjectStatus {
	IN_PROGRESS("In-Progress"), PUBLISHED("Published"), GRANTED("Granted"), PENDING("pending"), DRAFT("draft"),
	HOLD("Hold"), CANCEL("Cancelled"), REJECTED("Rejected");

	private String value;

	ProjectStatus(String status) {
		this.value = status;
	}

	public String getValue() {
		return value;
	}

	public static ProjectStatus convertToEnum(String value) {
		Optional<ProjectStatus> opValue = Arrays.stream(ProjectStatus.values())
				.filter(e -> e.getValue().equalsIgnoreCase(value))
				.findFirst();
		return opValue.isPresent() ? opValue.get() : null;
	}
}
