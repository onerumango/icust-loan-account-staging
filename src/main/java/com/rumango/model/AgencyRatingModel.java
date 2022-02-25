package com.rumango.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AgencyRatingModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int agencyId;
	private String agencyName;
	private String ratings;
	private String remarks;
}
