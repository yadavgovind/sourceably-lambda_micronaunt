package com.oms.projectbuddy.model.request;



import com.oms.projectbuddy.model.BankingInformation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BankingInformationRequest {
	private List<BankingInformation> supplierBankingInfoList;
	/*private Long id;
	private String userId;
	private String bankName;
	private String branchAddress;
	private String accountNumber;
	private String branchName;
	private String branchCode;
	private String beneficiaryName;
	private String ifscCode;
	private String micrCode;
	private String swiftCode;
	private Boolean isPrimaryAccount;
	private String lastUpdatedBy;
	private Long lastUpdatedEpochTime;*/

}
