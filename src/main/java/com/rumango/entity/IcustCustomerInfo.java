package com.rumango.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ICUST_CUSTOMER_INFO")
@Data
@XmlRootElement
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NoArgsConstructor
public class IcustCustomerInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4971390269031497315L;

	@Id
	@Column(name = "CUSTOMER_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "CUSTOMER_ID_SEQ", initialValue = 10000, allocationSize = 1)
	private Long customerId;

	@Column(name = "CUSTOMER_TYPE_ID")
	private String customerTypeId;

	@Column(name = "PHONE_NUMBER")
	private Long phoneNumber;

	@Column(name = "PRIMARY_EMAIL_ADDRESS")
	private String primaryEmailAdress;


	@Column(name = "PREFIX")
	private String prefix;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "MIDDLE_NAME")
	private String middleName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "DATE_OF_BIRTH")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "IST")
	private Date dateOfBirth;

	@Column(name = "MATERIAL_STATUS")
	private String materialStatus;

	@Column(name = "NATIONALITY")
	private String nationality;

	@Column(name = "KYC_REFERENCE")
	private String kycReference;


	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "CUSTOMER_ID")
	@OrderBy(value = "addressType asc")
	private Set<IcustCustomerAddress> userAddress;



	@Column(name = "SECONDARY_EMAIL_ADDRESS")
	private String secondaryEmailAdress;

	@Column(name = "LANDLINE_NUMBER")
	private String landlineNumber;

	@Column(name = "ACTIVE")
	private boolean active;
	@Column(name = "CREATED_BY")
	@NonNull
	private String createdBy;

	@Column(name = "CREATED_TIME")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd HH:mm:ss", timezone = "IST")
	@NonNull
	private Timestamp createdTime;

	@Column(name = "MODIFIED_BY")
	@NonNull
	private String modifiedBy;

	@Column(name = "MODIFIED_TIME")
	@NonNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd HH:mm:ss", timezone = "IST")
	private Timestamp modifiedTime;

	@Column(name = "CIF_NUMBER")
	private Long cifNumber;

	private Boolean existingCustomer;
	private Boolean primaryCustomer;

	
}
