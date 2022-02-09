package com.oms.projectbuddy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "b_certificate_percentage")
@Data
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
}
