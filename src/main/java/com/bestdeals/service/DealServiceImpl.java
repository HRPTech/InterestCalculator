package com.bestdeals.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bestdeals.model.Deal;
import com.bestdeals.repository.DealRepository;

@Service
public class DealServiceImpl implements DealService {
	private final Logger log = LoggerFactory.getLogger(DealServiceImpl.class);
	private DealRepository dealRepo;

	@Autowired
	public DealServiceImpl(DealRepository dealRepo) {
		super();
		this.dealRepo = dealRepo;
	}

	@Override
	public Long addDeal(Deal deal) {
		log.info("Add deal called {}", deal);
		// validation checks would take place here 
		return dealRepo.addDeal(deal);
	}

	@Override
	public Optional<Deal> get(Long dealId) {
		log.info("Deal id {} requested", dealId);
		return dealRepo.get(dealId);
	}

}
