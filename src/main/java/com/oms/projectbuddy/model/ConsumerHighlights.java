package com.oms.projectbuddy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "pb_consumer_company_highlights",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id"}),})
public class ConsumerHighlights {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "awards")
    private String awards;
    @Column(name = "special_program_projects")
    private String specialProgramProjects;
    @Column(name = "testimonials")
    private String Testimonials;
    @Column(name = "news_and_media")
    private String newsAndMedia;
    @Column(name = "last_updated_by")
    private String lastUpdatedBy;
    @Column(name = "last_created_epoch_time")
    private Long lastUpdatedEpochTime;
}
