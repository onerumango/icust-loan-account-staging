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
@Table(name = "ICUST_ACC_SERVICE_DETAILS")
@Data
@XmlRootElement
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NoArgsConstructor
public class IcustAccServiceDetails implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id
	@Column(name = "ACC_SERVICE_DETAILS_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acc_service_details_id_Sequence")
	@SequenceGenerator(name = "acc_service_details_id_Sequence", sequenceName = "ACC_SERVICE_DETAILS_ID")
	private Long accServiceDetailsId;
	private Long accountServiceId;
	private String serviceName;
	private Boolean ignoreHolidays;
	private String holidayCheck;
	private String moveAcrossMonth;
	private Boolean cascadeSchedules;
	

}
