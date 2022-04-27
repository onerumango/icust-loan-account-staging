package com.rumango.model;

import java.io.Serializable;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IcustLoanCreditRatingDetailsModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long loanAccountId;
	private String customerName;
	private Set<AgencyRatingModel> agencyRating;
	private Long cardId;
	private String accountType;
	
}
