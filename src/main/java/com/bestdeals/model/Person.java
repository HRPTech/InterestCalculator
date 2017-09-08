package com.bestdeals.model;

import com.excel.common_excel.annotation.ExcelCell;
import com.excel.helper.SpreadsheetCellHelper.Style;

public class Person {
	@ExcelCell(header = "Name", order = 0, width = 1000, style = Style.TITLE)
	private String name;
	@ExcelCell(header = "Deal Id", order = 1, style = Style.TWO_NUMBER_FORMAT, width = 2000)
	private int id;

	public Person(String name, int id) {
		super();
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

}
