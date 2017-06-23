package com.bestdeals.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bestdeals.enums.Currency;
import com.bestdeals.repository.FXRepository;
@Service
public class FXServiceImpl implements FXService {

	private FXRepository fxRepo;

	@Autowired
	public FXServiceImpl(FXRepository fxRepo) {
		
		this.fxRepo = fxRepo;
	}

	@Override
	public BigDecimal getFXRate(Currency fromCcy) {
		if (Currency.USD.equals(fromCcy)) {
			return BigDecimal.ONE;
		}

		return fxRepo.getRate(fromCcy, Currency.USD);
	}

}
