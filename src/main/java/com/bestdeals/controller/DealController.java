package com.bestdeals.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bestdeals.annotations.AccessCheck;
import com.bestdeals.annotations.Logged;
import com.bestdeals.exceptions.NotFoundException;
import com.bestdeals.model.Deal;
import com.bestdeals.model.SimpleInterestDeal;
import com.bestdeals.service.DealService;
import com.database.common.entity.DealEntity;
import com.database.common.service.EntityDatabaseService;
import com.database.common.service.EntityDatabaseServiceImpl;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = { "/api/deal" })
@Import({ EntityDatabaseServiceImpl.class })
public class DealController {

	@Autowired
	private DealService service;

	@Autowired
	private EntityDatabaseService dbService;

	@RequestMapping(value = "/{dealId}", method = RequestMethod.GET)
	@Logged
	public Deal getDeal(@PathVariable("dealId") final Long dealId) {
		Optional<Deal> deal = service.get(dealId);
		if (deal.isPresent()) {
			return deal.get();
		} else {
			throw new NotFoundException("Deal not found");
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	@Logged
	public Long addDeal(@ApiParam(value = "Deal", required = true) @RequestBody @Valid final Deal deal) {		
		return service.addDeal(deal);
	}

	@AccessCheck("PERMISSION TO DOWNLOAD EXCEL")
	@RequestMapping(value = "/workbook/excel", method = RequestMethod.GET)
	public void getAllDealsExcel(HttpServletResponse response) throws IOException {

		Workbook workbook = service.generateExcel();

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		workbook.write(byteArrayOutputStream);
		response.setContentLength(byteArrayOutputStream.size());
		response.setHeader("content-disposition", "attachment; filename=test.xlsx");
		response.setContentType("application/octet-stream;charset=UTF-8");
		byteArrayOutputStream.writeTo(response.getOutputStream());

	}
	
	@RequestMapping(value = "/excel", method = RequestMethod.GET)
	public ResponseEntity<Resource> getAllDealsExcel() throws IOException {
		ResponseEntity<Resource> response= service.getExcelSheetAsResource();	
		return response;

	}

	@RequestMapping(value = "/database/{dealId}", method = RequestMethod.GET)
	@Logged
	public Deal getDealFromDB(@PathVariable("dealId") final Long dealId) {
		Page<DealEntity> result = dbService.getData(dealId);		
		Deal d = new SimpleInterestDeal();
		d.setDealId(result.getContent().stream().findAny().get().getDealId());
		return d;
	}
	
	
}
