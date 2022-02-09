package com.oms.projectbuddy.utils;

import java.util.Arrays;
import java.util.Optional;

public enum StatusEnum {

	DELETED("Cancelled", "Deleted"),

	NDA_POSTED("NDA Posted", "NDA Posted"),
	NDA_APPROVED("NDA Approved", "NDA Approved"),
	NDA_REJECTED("NDA Rejected", "NDA Rejected"),

	WITHDRAW("Withdraw", "Withdraw"),
	NEGOTIATION("Negotiation", "Budget Review")
	, DUE_DILLIGENCE("Due Dilligence", "Due Dilligence"),
	PROJECT_GRANTED("Project Granted", "Project Granted"),
	PROPOSAL_SUBMISSION("Proposal Submission", "Submit Proposal"),
	PROPOSAL_SUBMITTED("Proposal Submitted", "Proposal Submitted"),
	PROPOSALS_REVIEW("Proposals Review", "Proposal Being Reviewed"),
	VENDOR_PRESENTATION("Vendor Presentation", "Vendor Presentation"),

	PUBLISH_TO_MARKET("Publish to Marketplace", "Submit NDA"),
	PUBLISH_NDA("Project Publised NDA", "Submit NDA"),
	RFP_DOCUMENTATION("RFP Documentation", "RFP Documentation"),

     //project milestoneEnums starts: 12
	PROVIDERS_TO_SIGN_NDA("Providers to sign NDA", "Accepting NDA"),
	DITRIBUTE_RFP_TO_PROVIDERS("Distribute RFP to providers", "Inviting Suppliers"),
	QUESTIONS_DEADLINE("Questions Deadline", "Questions Submission"),
	RFP_DEADLINE("RFP Deadline", "Submit Your Proposal"),
	INTERNAL_SHORTLISTING("Internal Shortlisting", "Proposal Being Reviewed"),
	REFERANCE_CHECK("Reference-Check, Due Diligence", "Due Diligence / Reference Check"),
	NOTIFY_SHORTLISTED_VENDORS("Notify Shortlisted Vendors", "Notify Shortlisted Vendors"),
	NEGOTIATED_BUDGET("Negotiated Budget & Documents", "Budget and Terms Review"),
	COMMUNICATE_FINAL_DECISION("Communicate final decision", "Final Decision"),
	CANCEL_PROJECT("Cancel Project", "Project Cancelled"),
    CONFIRMATION_TO_PARTICIPATE("Confirmation to Participate","Requesting Acceptance"),
	HOLD_PROJECT("Hold Project", "Project On Hold"),
	GRANTED("Granted", "Project Granted");

	private String customerValue;
	private String supplierValue;

	StatusEnum(String s, String value) {
		customerValue = s;

		this.supplierValue = value;
	}

	public String getSupplierValue() {
		return supplierValue;
	}

	public String getCustomerValue() {
		return customerValue;
	}

	/**
	 * @return the Enum representation for the given string.
	 * @throws IllegalArgumentException if unknown string.
	 */
	public static String customerValue(String s) throws IllegalArgumentException {
		return StatusEnum.valueOf(s).customerValue;

	}

	public static String supplierValue(String s) throws IllegalArgumentException {
		return StatusEnum.valueOf(s).supplierValue;

	}

	// Reverse lookup methods
	public static StatusEnum convertToEnum(String value) {
		
		Optional<StatusEnum> opValue = Arrays.stream(StatusEnum.values()).filter(accStatus -> accStatus.customerValue.equalsIgnoreCase(value)
				|| accStatus.supplierValue.equalsIgnoreCase(value)).findFirst();
		
		return opValue.isPresent()?opValue.get():null;
		
	}

}