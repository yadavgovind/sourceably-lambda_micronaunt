package com.oms.projectbuddy.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
public class ITCertificationUserHistoryDto {
    private String userId;

    private Long certificateId;

    private Long certificateDataId;

    private String editedArea;

    private String editedBy;

    private String editDetails;

    private Long historyCreatedEpochTime;
}
