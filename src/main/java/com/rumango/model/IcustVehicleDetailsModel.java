package com.rumango.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class IcustVehicleDetailsModel {
	private Long vehicleId;
	private Long loanAccountId;
	private String productName;
	private String hypothecatedBranch;
	private String vehicleClass;
	private Double marketValue;
	private String model;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM", timezone = "IST")
	private Date make;
	private String chassisNumber;
	private String engineNumber;
	private String registrationNumber;
	private String registrationState;
	private String registrationCity;
	private Double expectedSellingPrice;
	private String distanceRun;
	private String insuranceDetails;
	private String insuranceCompany;
	private String policyNumber;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd", timezone = "IST")
	private Date policyCommencementDate;
	private Double premiunAmount;
	private String premiunFrequency;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd", timezone = "IST")
	private Date policyRenewalDate;

}
