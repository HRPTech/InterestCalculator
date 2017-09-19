package com.bestdeals.repository;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.bestdeals.enums.Currency;

@Repository
public class FXRepositoryImpl implements FXRepository {

	private Map<Currency, BigDecimal> fxCache = new ConcurrentHashMap<>();

	/**
	 * Not a good example of storing the rates in a cache but just doing it to show how to enable caching
	 */
	@Override
	@Cacheable(value= "fxCache", key="#from + #to")
	public BigDecimal getRate(Currency from, Currency to) {
		if (from == null || to == null)
		{
			return BigDecimal.ONE;
		}
			
		if (from.equals(to)) {
			return BigDecimal.ONE;
		}

		if (to.equals(Currency.USD)) {
			return fxCache.compute(from, (k, v) -> v == null ? BigDecimal.ONE : v);
		}

		// Need to implement currency conversion logic here if the to currency
		// is not USD. Assuming the to currency is always USD
		return BigDecimal.ONE;
	}

	@Override
	public void addRate(Currency from, Currency to, BigDecimal rate) {
		if (to.equals(Currency.USD)) {
			fxCache.put(from, rate);
		}

	}

}
