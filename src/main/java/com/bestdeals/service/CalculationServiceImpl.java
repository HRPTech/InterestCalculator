package com.bestdeals.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bestdeals.exceptions.NotFoundException;
import com.bestdeals.model.Deal;
import com.bestdeals.repository.DealRepository;

@Service
public class CalculationServiceImpl implements CalculationService {

	private FXService fxService;
	private DealRepository dealRepo;

	@Autowired
	public CalculationServiceImpl(FXService fxService, DealRepository dealRepo) {
		super();
		this.fxService = fxService;
		this.dealRepo = dealRepo;
	}

	@Override
	public BigDecimal calculateInterestUSD(String clientId) {
		checkIfClientPresent(clientId);
		
		List<Deal> deals = dealRepo.getDealsByClientId(clientId);
		return calculateInterestUSD(deals);		
	}

	private BigDecimal calculateInterestUSD(List<Deal> deals) {
		return deals.stream().map(d -> ImmutablePair.of(d.getCcy(), d.getCalculation().apply(d)))
				.map(p -> fxService.getFXRate(p.getLeft()).multiply(p.getRight())).reduce(BigDecimal::add).get()
				.setScale(2, RoundingMode.HALF_UP);
	}

	private void checkIfClientPresent(String clientId) {
		if (!dealRepo.userExists(clientId)) {
			throw new NotFoundException("Client not found");
		}
	}

}
