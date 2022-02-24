package com.rumango.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="IC_LOAN_AGENCY_RATING")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AgencyRating implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agencyRating_seq")
	@SequenceGenerator(name = "agencyRating_seq", sequenceName = "agencyRating_seq", allocationSize = 1)
	private int agencyId;

	private String agencyName;
	private String ratings;
	private String remarks;
}
