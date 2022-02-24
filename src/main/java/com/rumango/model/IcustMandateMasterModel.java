package com.rumango.model;

import java.util.List;


import lombok.Data;

@Data
public class IcustMandateMasterModel {
	private Long mandateId;
	private Long loanAccountId;
	private Long noOfApplicants;
	private Boolean registered;
	private List<IcustMandateDetailsModel> mandateDetails;
}
