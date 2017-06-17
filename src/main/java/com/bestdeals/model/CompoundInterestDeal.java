package com.bestdeals.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.function.Function;

import com.bestdeals.enums.Currency;
import com.bestdeals.enums.DealType;

public class CompoundInterestDeal extends Deal {

    private final int numberOfYear; 
	private final BigDecimal rate;
	private final int frequency;
	
	public CompoundInterestDeal(String clientId, BigDecimal principal, Currency ccy,BigDecimal rate,
			int numberOfYear,int frequency, Date date) {
		super(clientId, DealType.COMPOUND_INTEREST, principal, ccy,date);
		this.numberOfYear=numberOfYear;
		this.rate=rate;	
		this.frequency=frequency;
	}

	@Override
	public Function<Deal, BigDecimal> getCalculation() {
		return d -> (d.getPrincipal().multiply(BigDecimal.ONE.add(rate.divide(BigDecimal.valueOf(numberOfYear))).pow(numberOfYear*frequency)))
				.subtract(d.getPrincipal());
	}
}
