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

	public String getAlternateNumber() {
		return alternateNumber;
	}

	public void setAlternateNumber(String alternateNumber) {
		this.alternateNumber = alternateNumber;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getAlternateEmail() {
		return alternateEmail;
	}

	public void setAlternateEmail(String alternateEmail) {
		this.alternateEmail = alternateEmail;
	}

	public String getPointOfContact() {
		return pointOfContact;
	}

	public void setPointOfContact(String pointOfContact) {
		this.pointOfContact = pointOfContact;
	}

	public String getEstablishedDate() {
		return establishedDate;
	}

	public void setEstablishedDate(String establishedDate) {
		this.establishedDate = establishedDate;
	}

	public String getCompanyWebsite() {
		return companyWebsite;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	public String getYearsOfRegistered() {
		return yearsOfRegistered;
	}

	public void setYearsOfRegistered(String yearsOfRegistered) {
		this.yearsOfRegistered = yearsOfRegistered;
	}

	public String getCompanyVideo() {
		return companyVideo;
	}

	public void setCompanyVideo(String companyVideo) {
		this.companyVideo = companyVideo;
	}

	public String getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(String stockPrice) {
		this.stockPrice = stockPrice;
	}

	public String getCeoName() {
		return ceoName;
	}

	public void setCeoName(String ceoName) {
		this.ceoName = ceoName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getTagLine() {
		return tagLine;
	}

	public void setTagLine(String tagLine) {
		this.tagLine = tagLine;
	}

	public String getCompanyDescription() {
		return companyDescription;
	}

	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}

	public String getFounders() {
		return founders;
	}

	public void setFounders(String founders) {
		this.founders = founders;
	}

	public String getHeadquartersCountry() {
		return headquartersCountry;
	}

	public void setHeadquartersCountry(String headquartersCountry) {
		this.headquartersCountry = headquartersCountry;
	}

	public String getHeadquartersState() {
		return headquartersState;
	}

	public void setHeadquartersState(String headquartersState) {
		this.headquartersState = headquartersState;
	}

	public String getHeadquartersCity() {
		return headquartersCity;
	}

	public void setHeadquartersCity(String headquartersCity) {
		this.headquartersCity = headquartersCity;
	}

	public String getProducts() {
		return products;
	}

	public void setProducts(String products) {
		this.products = products;
	}

	public String getSubsidiary() {
		return subsidiary;
	}

	public void setSubsidiary(String subsidiary) {
		this.subsidiary = subsidiary;
	}

	public Boolean getAgreeToShare() {
		return agreeToShare;
	}

	public void setAgreeToShare(Boolean agreeToShare) {
		this.agreeToShare = agreeToShare;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Long getUpdatedEpochTime() {
		return updatedEpochTime;
	}

	public void setUpdatedEpochTime(Long updatedEpochTime) {
		this.updatedEpochTime = updatedEpochTime;
	}
}
