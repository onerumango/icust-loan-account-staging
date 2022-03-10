package com.rumango.serviceImpl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.rumango.service.IcustPaymentService;

@Service
public class IcustPaymentServiceImpl implements IcustPaymentService {
	private static final Logger logger = Logger.getLogger(IcustPaymentServiceImpl.class);

	@Override
	public double getMonthlyInterestRate(long interestRate) {
		Long monthlyInterestRate = interestRate / 100 / 12;
		return (double) monthlyInterestRate;
	}

	@Override
	public Double principalPayment(double monthlyInterestRate, int paymentNumber, int durationMonths, Double loanAmount,
			int futureValues, int paymentType) {
		return pmt(monthlyInterestRate, durationMonths, loanAmount, futureValues, paymentType)
				- ipmt(monthlyInterestRate, paymentNumber, durationMonths, loanAmount, futureValues, paymentType);
	}

	@Override
	public Double interestPayment(double monthlyInterestRate, int paymentNumber, int durationMonths, Double loanAmount,
			int futureValues, int paymentType) {

		// Prior period (i.e., per-1) balance times periodic interest rate.
		// i.e., ipmt = fv(r, per-1, c, pv, type) * r
		// where c = pmt(r, nper, pv, fv, type)
		double ipmt = fv(monthlyInterestRate, paymentNumber - 1,
				pmt(monthlyInterestRate, durationMonths, loanAmount, futureValues, paymentType), futureValues,
				paymentType) * monthlyInterestRate;

		// account for payments at beginning of period versus end.
		if (paymentType == 1) {
			ipmt /= (1 + monthlyInterestRate);
		}

		// return results to caller.
		return ipmt;
	}

	public double pmt(double r, int nper, double pv, double fv, int type) {
		if (r == 0) {
			return -(pv + fv) / nper;
		}

		// i.e., pmt = r / ((1 + r)^N - 1) * -(pv * (1 + r)^N + fv)
		double pmt = r / (Math.pow(1 + r, nper) - 1) * -(pv * Math.pow(1 + r, nper) + fv);

		// account for payments at beginning of period versus end.
		if (type == 1) {
			pmt /= (1 + r);
		}

		// return results to caller.
		return pmt;
	}

	public double fv(double r, int nper, double c, double pv, int type) {
		if (r == 0)
			return pv;

		// account for payments at beginning of period versus end.
		// since we are going in reverse, we multiply by 1 plus interest rate.
		if (type == 1) {
			c *= (1 + r);
		}

		// fv = -(((1 + r)^N - 1) / r * c + pv * (1 + r)^N);
		double fv = -((Math.pow(1 + r, nper) - 1) / r * c + pv * Math.pow(1 + r, nper));

		// return results to caller.
		return fv;
	}

	public double ipmt(double r, int per, int nper, double pv, double fv, int type) {
		// Prior period (i.e., per-1) balance times periodic interest rate.
		// i.e., ipmt = fv(r, per-1, c, pv, type) * r
		// where c = pmt(r, nper, pv, fv, type)
		double ipmt = fv(r, per - 1, pmt(r, nper, pv, fv, type), pv, type) * r;

		// account for payments at beginning of period versus end.
		if (type == 1) {
			ipmt /= (1 + r);
		}

		// return results to caller.
		return ipmt;
	}

}
