package com.bestdeals.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.function.Function;

import com.bestdeals.enums.Currency;
import com.bestdeals.enums.DealType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class Deal {
	@JsonIgnore
	private Long dealId;
	private String clientId;
	private DealType dealType;
	private BigDecimal principal;
	private Currency ccy;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dealDate;

	public Deal(String clientId, DealType dealType, BigDecimal principal, Currency ccy, Date dealDate) {
		super();
		this.clientId = clientId;
		this.dealType = dealType;
		this.principal = principal;
		this.ccy = ccy;
		this.dealDate = dealDate;
	}

	public Deal() {
		// TODO Auto-generated constructor stub
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

	@JsonIgnore
	public DealType getDealType() {
		return dealType;
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

	public void setDealType(DealType dealType) {
		this.dealType = dealType;
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
		return "Deal [dealId=" + dealId + ", clientId=" + clientId + ", dealType=" + dealType + ", principal="
				+ principal + ", ccy=" + ccy + "]";
	}

}
