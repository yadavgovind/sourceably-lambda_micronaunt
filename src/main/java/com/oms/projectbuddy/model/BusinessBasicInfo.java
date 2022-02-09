package com.oms.projectbuddy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pb_user_company_information" , uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id"}),})
public class BusinessBasicInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "user_id")
	private String userId;
	@Column(name = "alternate_number")
	private String alternateNumber;
	@Column(name = "fax_number")
	private String faxNumber;
	@Column(name = "alternate_email")
	private String alternateEmail;
	@Column(name = "point_of_contact")
	private String pointOfContact;
	@Column(name = "established_date")
	private String establishedDate;
	@Column(name = "company_website")
	private String companyWebsite;
	@Column(name = "years_of_registered")
	private String yearsOfRegistered;
	@Column(name = "company_video")
	private String companyVideo;
	@Column(name = "stock_price")
	private String stockPrice;
	@Column(name = "ceoName")
	private String ceoName;
	@Column(name = "service_name")
	private String serviceName;
	@Column(name = "tag_line")
	private String tagLine;
	@Column(name = "company_description")
	private String companyDescription;
	@Column(name = "founders")
	private String founders;
	@Column(name = "headquarters_country")
	private String headquartersCountry;
	@Column(name = "headquarters_state")
	private String headquartersState;
	@Column(name = "headquarters_city")
	private String headquartersCity;
	//@Column(name = "headquarters")
	//private String headquarters;
	@Column(name = "products")
	private String products;
	@Column(name = "subsidiary")
	private String subsidiary;
	@Column(name = "agree_to_share")
	private Boolean agreeToShare;
	@Column(name = "last_updated_by")
	private String lastUpdatedBy;
	@Column(name = "updated_epoch_time")
	private Long updatedEpochTime;

	
	/*@OneToOne
	@JoinColumn(name = "company_id")
	@JsonIgnore
    private CompanyRegistration CompanyRegistration;*/

}
