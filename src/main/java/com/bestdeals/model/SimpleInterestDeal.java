package com.bestdeals.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.function.Function;

import com.bestdeals.enums.Currency;
import com.bestdeals.enums.DealType;

public class SimpleInterestDeal extends Deal {

	private final int numberOfYear;
	private final BigDecimal rate;

	public SimpleInterestDeal(String clientId, BigDecimal principal, Currency ccy, BigDecimal rate,
			int numberOfYear, Date date) {

		super(clientId, DealType.SIMPLE_INTEREST, principal, ccy, date);
		this.numberOfYear = numberOfYear;
		this.rate = rate;
	}

	@Override
	public Function<Deal, BigDecimal> getCalculation() {
		return d -> d.getPrincipal().multiply(rate).multiply(BigDecimal.valueOf(numberOfYear));
	}

}
