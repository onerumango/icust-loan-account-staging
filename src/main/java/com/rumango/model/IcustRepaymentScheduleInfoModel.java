package com.rumango.model;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class IcustRepaymentScheduleInfoModel {
	
	private int paymentNumber;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "IST")
    private Date paymentDate;
    private Double balance;
    private Double principalPaid;
    private Double interestPaid;
    private Double accumulatedInterest;

}
