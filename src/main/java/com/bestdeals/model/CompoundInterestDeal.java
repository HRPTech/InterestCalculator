package com.bestdeals.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
		super.setDealType(DealType.COMPOUND_INTEREST);
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
		BigDecimal val = (rate.divide(BigDecimal.valueOf(frequency),10,RoundingMode.HALF_UP)).add(BigDecimal.ONE).pow(frequency*numberOfYear);
		return d -> val.multiply(getPrincipal());

	}

	@Override
	public String toString() {
		return "CompoundInterestDeal [numberOfYear=" + numberOfYear + ", rate=" + rate + ", frequency=" + frequency
				+ " " + super.toString() + "]";
	}

}
