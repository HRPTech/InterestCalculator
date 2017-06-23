package com.bestdeals.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.function.Function;

import javax.validation.constraints.NotNull;

import com.bestdeals.enums.Currency;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Simple Interest Deal", parent = Deal.class)
public class SimpleInterestDeal extends Deal {
	@NotNull
	private Integer numberOfYears;
	@NotNull
	private BigDecimal rate;

	public SimpleInterestDeal() {
		super();

	}

	public SimpleInterestDeal(String clientId, BigDecimal principal, Currency ccy, BigDecimal rate, Integer numberOfYears,
			Date dealDate) {

		super(clientId, principal, ccy, dealDate);
		this.numberOfYears = numberOfYears;
		this.rate = rate;
	}

	public Integer getNumberOfYears() {
		return numberOfYears;
	}

	public void setNumberOfYears(Integer numberOfYears) {
		this.numberOfYears = numberOfYears;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	@Override
	public Function<Deal, BigDecimal> getCalculation() {
		return d -> d.getPrincipal().multiply(rate).multiply(BigDecimal.valueOf(numberOfYears));
	}

	@Override
	public String toString() {
		return "SimpleInterestDeal [numberOfYear=" + numberOfYears + ", rate=" + rate + " " + super.toString() + "]";
	}

}
