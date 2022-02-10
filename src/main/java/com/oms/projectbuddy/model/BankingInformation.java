package com.oms.projectbuddy.model;

import javax.persistence.*;

import io.micronaut.data.annotation.MappedEntity;
import lombok.Getter;
import lombok.Setter;

@MappedEntity
@Table(name = "banking_information")
public class BankingInformation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "bank_name")
	private String bankName;
	@Column(name = "user_id")
	private String userId;
	@Column(name = "account_type")
	private String accountType;
	@Column(name = "branch_address")
	private String branchAddress;
	@Column(name = "accunt_number")
	private String accountNumber;
	@Column(name = "branch_name")
	private String branchName;
	@Column(name = "branch_code")
	private String branchCode;
	@Column(name = "beneficiary_name")
	private String beneficiaryName;
	@Column(name = "ifsc_code")
	private String ifscCode;
	@Column(name = "micr_code")
	private String micrCode;
	@Column(name = "swift_code")
	private String swiftCode;
	@Column(name = "is_primary_account")
	private Boolean isPrimaryAccount;
	@Column(name = "last_updated_by")
	private String lastUpdatedBy;
	@Column(name = "last_updated_epoch_time")
	private Long lastUpdatedEpochTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getMicrCode() {
		return micrCode;
	}

	public void setMicrCode(String micrCode) {
		this.micrCode = micrCode;
	}

	public String getSwiftCode() {
		return swiftCode;
	}

	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}

	public Boolean getPrimaryAccount() {
		return isPrimaryAccount;
	}

	public void setPrimaryAccount(Boolean primaryAccount) {
		isPrimaryAccount = primaryAccount;
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
