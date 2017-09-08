package com.bestdeals.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.function.Function;

import javax.validation.constraints.NotNull;

import com.bestdeals.enums.Currency;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Compound Interest Deal", parent = Deal.class)
public class CompoundInterestDeal extends Deal {
	@NotNull
	private Integer numberOfYears;
	@NotNull
	private BigDecimal rate;
	@NotNull
	private Integer frequency;

	public CompoundInterestDeal() {
		super();		
	}
	
	public CompoundInterestDeal(String clientId, BigDecimal principal, Currency ccy, BigDecimal rate, Integer numberOfYears,
			Integer frequency, Date dealDate) {
		super(clientId, principal, ccy, dealDate);
		this.numberOfYears = numberOfYears;
		this.rate = rate;
		this.frequency = frequency;
	}
	
	public Integer getNumberOfYears() {
		return numberOfYears;
	}

	public void setNumberOfYear(Integer numberOfYears) {
		this.numberOfYears = numberOfYears;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	@Override
	public Function<Deal, BigDecimal> getCalculation() {
		BigDecimal val = (rate.divide(BigDecimal.valueOf(frequency),10,RoundingMode.HALF_UP)).add(BigDecimal.ONE).pow(frequency*numberOfYears);
		return d -> val.multiply(d.getPrincipal()).subtract(d.getPrincipal());

	}

	@Override
	public String toString() {
		return "CompoundInterestDeal [numberOfYear=" + numberOfYears + ", rate=" + rate + ", frequency=" + frequency
				+ " " + super.toString() + "]";
	}

}