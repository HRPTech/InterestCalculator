package com.bestdeals.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bestdeals.exceptions.NotFoundException;
import com.bestdeals.model.Deal;
import com.bestdeals.service.DealService;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = { "/api/deal" })
public class DealController {

	@Autowired
	private DealService service;

	@RequestMapping(value = "/{dealId}", method = RequestMethod.GET)
	public Deal getDeal(@PathVariable("dealId") final Long dealId) {
		Optional<Deal> deal = service.get(dealId);
		if (deal.isPresent()) {
			return deal.get();
		} else {
			throw new NotFoundException("Deal not found");
		}
	}

	@RequestMapping( method = RequestMethod.POST)
	public Long addDeal(@ApiParam(value = "Deal" ,required=true ) @RequestBody @Valid final Deal deal) {
		return service.addDeal(deal);
	}

}
