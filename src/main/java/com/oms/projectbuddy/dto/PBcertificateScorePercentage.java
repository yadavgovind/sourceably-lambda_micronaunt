/**
 * 
 */
package com.oms.projectbuddy.dto;

import lombok.*;

import java.util.List;

/**
 * @author pradeep maurya.
 *
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PBcertificateScorePercentage {

	private String certificateName;
	private Double certificatePercentage;
	private Long certificateId;
	private Long totalCert;
	private Long totalAttemptCert;
	private Double averageScore;
	private List<ChildCertification> childCertifications;

	@Data
	@Builder
	public static class  ChildCertification{
		private String certificateName;
		private Long certificateId;
		private Double score;
		
		private String userId;
	}

}

