package com.bestdeals.repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Repository;

import com.bestdeals.model.Deal;

@Repository
public class DealRepositoryImpl implements DealRepository {

	private Map<String, Set<Deal>> dealsByClient = new HashMap<>();
	private Map<Long, Deal> dealMap = new HashMap<>();

	private final AtomicLong dealId = new AtomicLong(0);
	private final ReadWriteLock rwLock = new ReentrantReadWriteLock(true);
	private final Lock readLock = rwLock.readLock();
	private final Lock writeLock = rwLock.writeLock();

	@Override
	public List<Deal> getDealsByClientId(String clientId) {
		readLock.lock();
		try {
			Set<Deal> deals = dealsByClient.getOrDefault(clientId, Collections.emptySet());
			return Collections.unmodifiableList(deals.stream().map(d -> ObjectUtils.clone(d)).collect(Collectors.toList()));
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public Long addDeal(Deal deal) {
		writeLock.lock();
		try {
			Long id = dealId.incrementAndGet();
			deal.setDealId(id);
			dealMap.put(id, deal);
			dealsByClient.compute(deal.getClientId(), (k, v) -> addDeal().apply(v, deal));
			return id;
		} finally {
			writeLock.unlock();
		}
	}

	private BiFunction<Set<Deal>, Deal, Set<Deal>> addDeal() {
		return (deals, deal) -> {
			if (deals == null) {
				deals = new HashSet<Deal>();
			}
			deals.add(deal);
			return deals;
		};
	}

	@Override
	public Optional<Deal> get(Long dealId) {
		readLock.lock();
		try {
			return Optional.ofNullable((dealMap.get(dealId)));
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public Boolean userExists(String clientId) {
		return dealsByClient.containsKey(clientId);
	}

}
