package com.bestdeals.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bestdeals.annotations.Logged;
import com.bestdeals.service.CalculationService;

@RestController
@RequestMapping(value={"/api/calculation/interest"})
public class InterestController {

	
	@Autowired
	private CalculationService service;
	
	@Logged
	@RequestMapping(value="/{clientId}",method=RequestMethod.GET)
	public BigDecimal getCompoundInterestForClient(@PathVariable("clientId") final String clientId){
		return service.calculateInterestUSD(clientId);
	}	
	
}
