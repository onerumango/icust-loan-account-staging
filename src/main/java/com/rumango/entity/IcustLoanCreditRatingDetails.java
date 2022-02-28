package com.rumango.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "IC_LOAN_CREDIT_RATING")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IcustLoanCreditRatingDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "creditRatingId_seq")
	@SequenceGenerator(name = "creditRatingId_seq", sequenceName = "creditRatingId_seq", allocationSize = 1)
	private Long creditRatingId;

	@Column(name = "LOAN_ACC_ID")
	private Long loanAccountId;

	private String customerName;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "LOAN_ACC_ID")
	private Set<AgencyRating> agencyRating;

}
