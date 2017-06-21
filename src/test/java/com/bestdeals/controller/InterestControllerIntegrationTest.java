package com.bestdeals.controller;

import static org.hamcrest.CoreMatchers.equalTo;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bestdeals.enums.Currency;
import com.bestdeals.model.CompoundInterestDeal;
import com.bestdeals.model.SimpleInterestDeal;
import com.bestdeals.repository.DealRepository;
import com.bestdeals.repository.FXRepository;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.DEFINED_PORT)
public class InterestControllerIntegrationTest {

	@Autowired
	private DealRepository repo;
	
	@Autowired
	private FXRepository fxRepo;

	@Before
	public void setUp() {
		repo.addDeal(new CompoundInterestDeal("123LR_HIRAN", BigDecimal.valueOf(100), Currency.USD,
				BigDecimal.valueOf(0.05), 2, 4, new Date()));
		repo.addDeal(new CompoundInterestDeal( "123LR_HIRAN", BigDecimal.valueOf(-100), Currency.GBP, BigDecimal.valueOf(0.05), 2, 4, new Date()));
		repo.addDeal(new SimpleInterestDeal( "123LR_HIRAN", BigDecimal.valueOf(100), Currency.GBP, BigDecimal.valueOf(0.05), 2, new Date()));
	
		repo.addDeal(new CompoundInterestDeal("123LR_HIRAN", BigDecimal.valueOf(100), Currency.USD,
				BigDecimal.valueOf(0.05), 2, 4, new Date()));
		
		fxRepo.addRate(Currency.GBP, Currency.USD, BigDecimal.valueOf(1.20));
		fxRepo.addRate(Currency.EUR, Currency.USD, BigDecimal.valueOf(0.12320));
		
		RequestSpecification requestSpecification = new RequestSpecBuilder().setPort(8092).build();
		RestAssured.requestSpecification = requestSpecification;
	}

	@Test
	public void testInterestForUser() throws Exception {
		RestAssured.given().when().header("Content-Type", "application/json").get("/api/calculation/interest/123LR_HIRAN").then()
				.assertThat().statusCode(HttpStatus.SC_OK).body(equalTo("20.36"));

	}

}
