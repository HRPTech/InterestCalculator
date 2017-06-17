package com.bestdeals.repository;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;

import com.bestdeals.enums.Currency;
import com.bestdeals.model.CompoundInterestDeal;
import com.bestdeals.model.Deal;
import com.bestdeals.model.SimpleInterestDeal;

public class DealRepositoryTest {

	DealRepository repo = new DealRepositoryImpl();
	
	@Test
	public void testAddSimpleDeal(){
		Deal simple = new SimpleInterestDeal( "123LR_HIRAN", BigDecimal.valueOf(100), Currency.GBP, BigDecimal.valueOf(0.05), 2, new Date());
		repo.addDeal(simple);
		assertEquals(1,repo.getDealsByClientId("123LR_HIRAN").size());
		
	}
	
	@Test
	public void testAddCompoundDeal(){
		Deal compound = new CompoundInterestDeal( "123LR_HIRAN", BigDecimal.valueOf(100), Currency.GBP, BigDecimal.valueOf(0.05), 2, 4, new Date());
		repo.addDeal(compound);
		assertEquals(1,repo.getDealsByClientId("123LR_HIRAN").size());		
	}
	
	@Test
	public void testAddManyDeal(){
		Deal compound = new CompoundInterestDeal( "123LR_HIRAN", BigDecimal.valueOf(100), Currency.GBP, BigDecimal.valueOf(0.05), 2, 4, new Date());
		Deal simple = new SimpleInterestDeal( "123LR_HIRAN", BigDecimal.valueOf(100), Currency.GBP, BigDecimal.valueOf(0.05), 2, new Date());
		Deal simple1 = new SimpleInterestDeal("123LR_XYZ", BigDecimal.valueOf(100), Currency.EUR, BigDecimal.valueOf(0.15), 1, new Date());
		Deal simpl2 = new SimpleInterestDeal( "123LR_HIRAN", BigDecimal.valueOf(100), Currency.GBP, BigDecimal.valueOf(0.05), 2, new Date());
		Deal compound1 = new CompoundInterestDeal( "123LR_HIRAN", BigDecimal.valueOf(100), Currency.GBP, BigDecimal.valueOf(0.05), 2, 4, new Date());
				
		repo.addDeal(compound);
		repo.addDeal(simple);
		repo.addDeal(simple1);
		repo.addDeal(simpl2);
		repo.addDeal(compound1);		
		
		assertEquals(4,repo.getDealsByClientId("123LR_HIRAN").size());	
		assertEquals(1,repo.getDealsByClientId("123LR_XYZ").size());
	}
	
	@Test
	public void testDealNotPresent(){				
		assertEquals(0,repo.getDealsByClientId("123LR_HIRAN").size());			
	}
	
	@Test
	public void testUserExists(){
		Deal compound = new CompoundInterestDeal( "123LR_HIRAN", BigDecimal.valueOf(100), Currency.GBP, BigDecimal.valueOf(0.05), 2, 4, new Date());
		repo.addDeal(compound);
		assertTrue(repo.userExists("123LR_HIRAN"));
	}
	
	@Test
	public void testUserNotExists(){		
		assertFalse(repo.userExists("123LR"));
	}
	
}
