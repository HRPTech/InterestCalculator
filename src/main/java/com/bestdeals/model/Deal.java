package com.bestdeals.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.function.Function;

import com.bestdeals.enums.Currency;
import com.bestdeals.enums.DealType;
import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class Deal {

	private Long dealId;
	private final String clientId;
	private final DealType dealType;
	private final BigDecimal principal;
	private final Currency ccy;
	private final Date dealDate;

	public Deal(String clientId, DealType dealType, BigDecimal principal, Currency ccy,Date dealDate) {
		super();	
		this.clientId = clientId;
		this.dealType = dealType;
		this.principal = principal;
		this.ccy = ccy;		
		this.dealDate=dealDate;
	}
	
	@JsonIgnore
	public abstract Function<Deal, BigDecimal> getCalculation();

		
	public void setDealId(Long dealId) {
		this.dealId = dealId;
	}

	public Long getDealId() {
		return dealId;
	}

	public String getClientId() {
		return clientId;
	}

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
