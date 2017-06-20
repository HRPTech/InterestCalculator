package com.bestdeals.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.function.Function;

import com.bestdeals.enums.Currency;
import com.bestdeals.enums.DealType;

public class SimpleInterestDeal extends Deal {

	private int numberOfYear;
	private BigDecimal rate;

	public SimpleInterestDeal() {
		super();
	}

	public SimpleInterestDeal(String clientId, BigDecimal principal, Currency ccy, BigDecimal rate, int numberOfYear,
			Date dealDate) {

		super(clientId, DealType.SIMPLE_INTEREST, principal, ccy, dealDate);
		this.numberOfYear = numberOfYear;
		this.rate = rate;
	}	
	
	public int getNumberOfYear() {
		return numberOfYear;
	}

	public void setNumberOfYear(int numberOfYear) {
		this.numberOfYear = numberOfYear;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	@Override
	public Function<Deal, BigDecimal> getCalculation() {
		return d -> d.getPrincipal().multiply(rate).multiply(BigDecimal.valueOf(numberOfYear));
	}

}
