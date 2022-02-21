package com.rumango.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ICUST_VEHICLE_DETAILS")
@Data
@XmlRootElement
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NoArgsConstructor
public class IcustVehicleDetails implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "VEHICLE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_id_Sequence")
	@SequenceGenerator(name = "vehicle_id_Sequence", sequenceName = "VEHICLE_ID_SEQ")
	private Long vehicleId;
	private Long loanId;
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
