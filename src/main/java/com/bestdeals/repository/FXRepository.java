package com.bestdeals.repository;

import java.math.BigDecimal;

import com.bestdeals.enums.Currency;

public interface FXRepository {
	BigDecimal getRate(Currency from, Currency to);

	void addRate(Currency from, Currency to, BigDecimal rate);

}
