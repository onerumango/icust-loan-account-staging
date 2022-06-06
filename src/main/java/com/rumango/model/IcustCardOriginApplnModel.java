package com.rumango.model;

import java.util.List;

import lombok.Data;

@Data
public class IcustCardOriginApplnModel {
	private IcustCardInitiationModel initiationInfo;
	private List<IcustFinancialDetailsModel>  financialInfo;
	private List<IcustCardPreferencesModel> preferenceInfo;
}
