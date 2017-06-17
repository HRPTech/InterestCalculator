package com.bestdeals.repository;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.bestdeals.enums.Currency;

public class FXRepositoryTest {

	FXRepository repo = new FXRepositoryImpl();
	
	@Test
	public void testAddRate(){
		repo.addRate(Currency.GBP, Currency.USD, BigDecimal.valueOf(1.20));
		assertEquals(BigDecimal.valueOf(1.20),repo.getRate(Currency.GBP, Currency.USD));
	}
	
	@Test
	public void testAddRates(){
		repo.addRate(Currency.GBP, Currency.USD, BigDecimal.valueOf(1.20));
		repo.addRate(Currency.EUR, Currency.USD, BigDecimal.valueOf(0.12320));
		assertEquals(BigDecimal.valueOf(1.20),repo.getRate(Currency.GBP, Currency.USD));
		assertEquals(BigDecimal.valueOf(0.12320),repo.getRate(Currency.EUR, Currency.USD));
	}
	
}
