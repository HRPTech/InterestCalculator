package com.bestdeals.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.function.Function;

import com.bestdeals.enums.Currency;
import com.bestdeals.enums.DealType;

public class CompoundInterestDeal extends Deal {

	private int numberOfYear;
	private BigDecimal rate;
	private int frequency;

	
	public CompoundInterestDeal() {
		super();
	}
	
	public CompoundInterestDeal(String clientId, BigDecimal principal, Currency ccy, BigDecimal rate, int numberOfYear,
			int frequency, Date dealDate) {
		super(clientId, DealType.COMPOUND_INTEREST, principal, ccy, dealDate);
		this.numberOfYear = numberOfYear;
		this.rate = rate;
		this.frequency = frequency;
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

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	@Override
	public Function<Deal, BigDecimal> getCalculation() {
		return d -> (d.getPrincipal().multiply(
				BigDecimal.ONE.add(rate.divide(BigDecimal.valueOf(numberOfYear))).pow(numberOfYear * frequency)))
						.subtract(d.getPrincipal());
	}
}
