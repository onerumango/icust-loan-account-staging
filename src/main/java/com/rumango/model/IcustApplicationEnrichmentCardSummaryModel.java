package com.rumango.model;

import java.util.List;

import lombok.Data;

@Data
public class IcustApplicationEnrichmentCardSummaryModel {
	private List<IcustCardInterestModel> intererstInfo;
	private List<IcustCardChargeModel> chargeInfo;
}
