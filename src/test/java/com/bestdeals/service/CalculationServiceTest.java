package com.bestdeals.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.bestdeals.enums.Currency;
import com.bestdeals.exceptions.NotFoundException;
import com.bestdeals.model.CompoundInterestDeal;
import com.bestdeals.model.Deal;
import com.bestdeals.model.SimpleInterestDeal;
import com.bestdeals.repository.DealRepository;
import com.bestdeals.repository.FXRepository;

public class CalculationServiceTest {

	@Mock
	private FXRepository fxRepo;
	@Mock
	private FXService fxService;
	@Mock
	private DealRepository dealRepo;
	private CalculationService calcService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		calcService = new CalculationServiceImpl(fxService, dealRepo);		
	}
	
	@Test
	public void testCalculateInterestForClient(){
		Mockito.when(dealRepo.userExists(Mockito.anyString())).thenReturn(true);
		Mockito.when(dealRepo.getDealsByClientId(Mockito.anyString())).thenReturn(getDeals());
		Mockito.when(fxService.getFXRate(Currency.GBP)).thenReturn(BigDecimal.valueOf(0.234));
		Mockito.when(fxService.getFXRate(Currency.EUR)).thenReturn(BigDecimal.valueOf(1.4));
		Mockito.when(fxService.getFXRate(Currency.USD)).thenReturn(BigDecimal.valueOf(1));
		
		BigDecimal interest = calcService.calculateInterestUSD("TEST");
		assertEquals(BigDecimal.valueOf(7719.54),interest);
		
	}
	
	@Test(expected=NotFoundException.class)
	public void testClientNotExist(){
		Mockito.when(dealRepo.userExists(Mockito.anyString())).thenReturn(false);
		Mockito.when(dealRepo.getDealsByClientId(Mockito.anyString())).thenReturn(getDeals());
		Mockito.when(fxService.getFXRate(Currency.GBP)).thenReturn(BigDecimal.valueOf(0.234));
		Mockito.when(fxService.getFXRate(Currency.EUR)).thenReturn(BigDecimal.valueOf(1.4));
			
		 calcService.calculateInterestUSD("TEST");	
		
	}
	
	public List<Deal> getDeals(){
		List<Deal> deals = new ArrayList<>();
		Deal compound = new CompoundInterestDeal( "123LR_HIRAN", BigDecimal.valueOf(-100), Currency.GBP, BigDecimal.valueOf(0.05), 2, 4, new Date());
		Deal simple = new SimpleInterestDeal( "123LR_HIRAN", BigDecimal.valueOf(100), Currency.GBP, BigDecimal.valueOf(0.05), 2, new Date());
		Deal simple1 = new SimpleInterestDeal( "123LR_XYZ", BigDecimal.valueOf(100), Currency.EUR, BigDecimal.valueOf(0.15), 1, new Date());
		Deal simpl2 = new SimpleInterestDeal( "123LR_HIRAN", BigDecimal.valueOf(-400), Currency.GBP, BigDecimal.valueOf(0.05), 2, new Date());
		Deal compound1 = new CompoundInterestDeal( "123LR_HIRAN", BigDecimal.valueOf(7000), Currency.USD, BigDecimal.valueOf(0.05), 2, 4, new Date());
	
		deals.add(compound);
		deals.add(simple);
		deals.add(simple1);
		deals.add(simpl2);
		deals.add(compound1);	
		return deals;
	}

}
