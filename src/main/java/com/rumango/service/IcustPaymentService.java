package com.rumango.service;

public interface IcustPaymentService {

	double getMonthlyInterestRate(long interestRate);

	Double principalPayment(double monthlyInterestRate, int paymentNumber, int durationMonths, Double loanAmount, int futureValues, int paymentType);

	Double interestPayment(double monthlyInterestRate, int paymentNumber, int durationMonths, Double loanAmount, int futureValues, int paymentType);

}
