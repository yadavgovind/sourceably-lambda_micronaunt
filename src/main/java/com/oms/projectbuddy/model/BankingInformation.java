package com.oms.projectbuddy.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
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

}
