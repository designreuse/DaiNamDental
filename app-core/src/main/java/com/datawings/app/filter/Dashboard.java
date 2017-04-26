package com.datawings.app.filter;

import com.datawings.app.common.BeanUtil;

public class Dashboard {
	private String month;
	private Integer nrtd;
	private Double weightGross;
	private Double weightCharge;
	private Double amount;

	public Dashboard() {
		init();
	}

	public void init() {
		BeanUtil.initSimplePropertyBean(this);
	}

	public String getMonth() {
		return month;
	}

	public Integer getNrtd() {
		return nrtd;
	}

	public Double getAmount() {
		return amount;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setNrtd(Integer nrtd) {
		this.nrtd = nrtd;
	}

	public Double getWeightGross() {
		return weightGross;
	}

	public Double getWeightCharge() {
		return weightCharge;
	}

	public void setWeightGross(Double weightGross) {
		this.weightGross = weightGross;
	}

	public void setWeightCharge(Double weightCharge) {
		this.weightCharge = weightCharge;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
