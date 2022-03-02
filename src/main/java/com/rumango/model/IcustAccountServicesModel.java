package com.rumango.model;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class IcustAccountServicesModel {
	private Long accServiceId;
	private Long loanAccountId;
	private String statementCycle;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd", timezone = "IST")
	private Date startDate;
	private String statementType;
	private List<IcustAccServiceDetailsModel> serviceDetails;
}
