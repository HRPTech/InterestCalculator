package com.bestdeals.controller;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bestdeals.service.CalculationService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class InterestControllerTest {

	@InjectMocks
	InterestController controller;
	
	@Mock
	private CalculationService service;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		this.mockMvc=MockMvcBuilders.standaloneSetup(controller).build();
	}	
	
	@Test
	public void testInterestForUser() throws Exception{
		Mockito.when(service.calculateInterestUSD(Mockito.anyString())).thenReturn(BigDecimal.ONE);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/calculation/interest/1")).andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
}
