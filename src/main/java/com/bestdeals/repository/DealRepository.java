package com.bestdeals.repository;

import java.util.List;
import java.util.Optional;

import com.bestdeals.model.Deal;

public interface DealRepository {

	Long addDeal(Deal deal);

	Boolean userExists(String clientId);

	List<Deal> getDealsByClientId(String clientId);

	Optional<Deal> get(Long dealId);

	List<Deal> getAllDeals();
}
