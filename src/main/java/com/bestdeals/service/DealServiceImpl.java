package com.bestdeals.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bestdeals.model.Deal;
import com.bestdeals.repository.DealRepository;

@Service
public class DealServiceImpl implements DealService {

	private DealRepository dealRepo;

	@Autowired
	public DealServiceImpl(DealRepository dealRepo) {
		super();
		this.dealRepo = dealRepo;
	}

	@Override
	public Long addDeal(Deal deal) {
		// validation checks would take place here 
		return dealRepo.addDeal(deal);
	}

	@Override
	public Optional<Deal> get(Long dealId) {
		return dealRepo.get(dealId);
	}

}
