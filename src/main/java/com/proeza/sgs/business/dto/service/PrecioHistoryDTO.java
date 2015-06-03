package com.proeza.sgs.business.dto.service;

import java.io.Serializable;
import java.util.List;

public class PrecioHistoryDTO implements Serializable {

	private static final long	serialVersionUID	= 1L;

	private List<Double>	  prices;

	public PrecioHistoryDTO () {
	}

	public PrecioHistoryDTO (List<Double> prices) {
		this.prices = prices;
	}

	public List<Double> getPrices () {
		return this.prices;
	}

	public void setPrices (List<Double> prices) {
		this.prices = prices;
	}
}