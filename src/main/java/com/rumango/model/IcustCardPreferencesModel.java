package com.rumango.model;

import lombok.Data;

@Data
public class IcustCardPreferencesModel {
	private Long preferenceId;
	private String limitType;
	private Double dailyLimit;
	private Long limitPerTransaction;
}
