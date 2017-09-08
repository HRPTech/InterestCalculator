package com.bestdeals.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.function.Function;

import javax.validation.constraints.NotNull;

import com.bestdeals.enums.Currency;
import com.excel.common_excel.annotation.ExcelCell;
import com.excel.helper.SpreadsheetCellHelper.Style;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import io.swagger.annotations.ApiModel;

@JsonTypeInfo(use = Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({ @Type(value = CompoundInterestDeal.class, name = "COMPOUND"),
		@Type(value = SimpleInterestDeal.class, name = "SIMPLE"), })
@ApiModel(subTypes = { CompoundInterestDeal.class, SimpleInterestDeal.class }, discriminator = "type")
public abstract class Deal {
	@JsonIgnore
	@ExcelCell(header = "Deal Id", order = 0, width = 1000, style = Style.TITLE)
	private Long dealId;
	@NotNull
	@ExcelCell(header = "Client Id", order = 1, width = 1000)
	private String clientId;
	@NotNull
	@ExcelCell(header = "Principal", order = 2, width = 1000, style=Style.TWO_NUMBER_FORMAT)
	private BigDecimal principal;
	@NotNull
	@ExcelCell(header = "Currency", order = 3, width = 1000)
	private Currency ccy;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull
	@ExcelCell(header = "Date", order = 4, width = 1000)
	private Date dealDate;

	public Deal(String clientId, BigDecimal principal, Currency ccy, Date dealDate) {
		super();
		this.clientId = clientId;
		this.principal = principal;
		this.ccy = ccy;
		this.dealDate = dealDate;
	}

	public Deal() {

	}

	@JsonIgnore
	public abstract Function<Deal, BigDecimal> getCalculation();

	@JsonIgnore
	public void setDealId(Long dealId) {
		this.dealId = dealId;
	}

	@JsonIgnore
	public Long getDealId() {
		return dealId;
	}

	public String getClientId() {
		return clientId;
	}

	public BigDecimal getPrincipal() {
		return principal;
	}

	public Currency getCcy() {
		return ccy;
	}

	public Date getDealDate() {
		return dealDate;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setPrincipal(BigDecimal principal) {
		this.principal = principal;
	}

	public void setCcy(Currency ccy) {
		this.ccy = ccy;
	}

	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dealId == null) ? 0 : dealId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Deal other = (Deal) obj;
		if (dealId == null) {
			if (other.dealId != null)
				return false;
		} else if (!dealId.equals(other.dealId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Deal [dealId=" + dealId + ", clientId=" + clientId + ",  principal=" + principal + ", ccy=" + ccy + "]";
	}

}