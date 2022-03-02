package com.rumango.model;

import lombok.Data;

@Data
public class IcustAccServiceDetailsModel {
	private Long accServiceDetailsId;
	private Long accountServiceId;
	private String serviceName;
	private Boolean ignoreHolidays;
	private String holidayCheck;
	private String moveAcrossMonth;
	private Boolean cascadeSchedules;
}
