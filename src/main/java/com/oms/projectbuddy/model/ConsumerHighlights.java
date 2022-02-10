package com.oms.projectbuddy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getSpecialProgramProjects() {
        return specialProgramProjects;
    }

    public void setSpecialProgramProjects(String specialProgramProjects) {
        this.specialProgramProjects = specialProgramProjects;
    }

    public String getTestimonials() {
        return Testimonials;
    }

    public void setTestimonials(String testimonials) {
        Testimonials = testimonials;
    }

    public String getNewsAndMedia() {
        return newsAndMedia;
    }

    public void setNewsAndMedia(String newsAndMedia) {
        this.newsAndMedia = newsAndMedia;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Long getLastUpdatedEpochTime() {
        return lastUpdatedEpochTime;
    }

    public void setLastUpdatedEpochTime(Long lastUpdatedEpochTime) {
        this.lastUpdatedEpochTime = lastUpdatedEpochTime;
    }
}
