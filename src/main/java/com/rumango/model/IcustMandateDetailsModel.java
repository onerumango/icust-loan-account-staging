package com.rumango.model;

import lombok.Data;

@Data
public class IcustMandateDetailsModel {
	private Long mandateDetailsId;
	private String applicantName;
	private Double collateralShare;
	private Double repaymentShare;
	private Long mandateId;
}
