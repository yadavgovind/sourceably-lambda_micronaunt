package com.oms.projectbuddy.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "pb_itcertification_user_history")
public class ITCertificationUserHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "certificate_id")
    private Long certificateId;

    @Column(name = "certificate_data_id")
    private Long certificateDataId;

    @Column(name = "edited_area")
    private String editedArea;

    @Column(name = "edited_By")
    private String editedBy;

    @Column(name = "edit_details")
    private String editDetails;

    @Column(name = "history_created_epoch_time")
    private Long historyCreatedEpochTime;


}
