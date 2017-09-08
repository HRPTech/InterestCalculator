package com.bestdeals.service;

import java.util.Optional;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import com.bestdeals.model.Deal;

public interface DealService {

	Long addDeal(Deal deal);

	Optional<Deal> get(Long dealId);

	Workbook generateExcel();

	ResponseEntity<Resource> getExcelSheetAsResource();
}
