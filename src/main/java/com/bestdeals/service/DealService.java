package com.bestdeals.service;

import java.util.Optional;

import com.bestdeals.model.Deal;

public interface DealService {
	
	 Long addDeal(Deal deal) ;
	 Optional<Deal> get(Long dealId) ;
}
