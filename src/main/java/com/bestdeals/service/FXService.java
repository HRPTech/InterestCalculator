package com.bestdeals.service;

import java.math.BigDecimal;

import com.bestdeals.enums.Currency;

public interface FXService {

	BigDecimal getFXRate(Currency fromCcy);
}
