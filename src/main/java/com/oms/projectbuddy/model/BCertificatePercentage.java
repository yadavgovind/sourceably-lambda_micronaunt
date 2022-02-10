package com.oms.projectbuddy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "b_certificate_percentage")

@NoArgsConstructor
@AllArgsConstructor
public class BCertificatePercentage {

    @Id
    @Column(
            name = "id",
            unique = true,
            nullable = false,
            columnDefinition = "UUID"
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long attachedScore;
    private Long commentScore;
    private Long sourceablyVerifiedScore;
    private Long validDocumentDateScore;

   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "pb_certification_data_id", referencedColumnName = "bCertificateDataId")
    private ProviderBCertificateData  providerBCertificateData;

    public BCertificatePercentage(ProviderBCertificateData providerBCertificateData){
        this.providerBCertificateData=providerBCertificateData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAttachedScore() {
        return attachedScore;
    }

    public void setAttachedScore(Long attachedScore) {
        this.attachedScore = attachedScore;
    }

    public Long getCommentScore() {
        return commentScore;
    }

    public void setCommentScore(Long commentScore) {
        this.commentScore = commentScore;
    }

    public Long getSourceablyVerifiedScore() {
        return sourceablyVerifiedScore;
    }

    public void setSourceablyVerifiedScore(Long sourceablyVerifiedScore) {
        this.sourceablyVerifiedScore = sourceablyVerifiedScore;
    }

    public Long getValidDocumentDateScore() {
        return validDocumentDateScore;
    }

    public void setValidDocumentDateScore(Long validDocumentDateScore) {
        this.validDocumentDateScore = validDocumentDateScore;
    }

    public ProviderBCertificateData getProviderBCertificateData() {
        return providerBCertificateData;
    }

    public void setProviderBCertificateData(ProviderBCertificateData providerBCertificateData) {
        this.providerBCertificateData = providerBCertificateData;
    }
}
