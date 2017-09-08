package com.bestdeals.service;

import java.util.Optional;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bestdeals.model.Deal;
import com.bestdeals.model.Person;
import com.bestdeals.repository.DealRepository;
import com.database.common.service.EntityDatabaseService;
import com.database.common.service.EntityDatabaseServiceImpl;
import com.excel.common_excel.service.ExcelService;
import com.excel.common_excel.service.ExcelServiceImpl;

@Service
@Import({ ExcelServiceImpl.class,EntityDatabaseServiceImpl.class })
public class DealServiceImpl implements DealService {
	private final Logger log = LoggerFactory.getLogger(DealServiceImpl.class);
	private DealRepository dealRepo;
	private ExcelService excelService;
	private EntityDatabaseService dbService;

	@Autowired
	public DealServiceImpl(DealRepository dealRepo,ExcelService excelService,EntityDatabaseService dbService) {		
		this.dealRepo = dealRepo;
		this.excelService=excelService;
		this.dbService=dbService;
	}

	@Override
	public Long addDeal(Deal deal) {
		log.info("Add deal called {}", deal);		
		return dealRepo.addDeal(deal);
	}

	@Override
	public Optional<Deal> get(Long dealId) {
		log.info("Deal id {} requested", dealId);
		dbService.getData(dealId);
		return dealRepo.get(dealId);
	}
	
	public Workbook generateExcel(){		
		return excelService.getExcelSheet(dealRepo.getAllDeals(), Person.class, "Deals", "DealsSheet");
				
	}
	
	public ResponseEntity<Resource> getExcelSheetAsResource(){
		return excelService.getExcelSheetAsResource(dealRepo.getAllDeals(), Person.class, "Deals", "DealsSheet");
	}

}
