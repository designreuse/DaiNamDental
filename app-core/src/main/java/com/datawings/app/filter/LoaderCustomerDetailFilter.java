package com.datawings.app.filter;

import java.util.Date;

import com.datawings.app.common.BeanUtil;

public class LoaderCustomerDetailFilter {
	
	private Integer number;
	private Date dateExcute;
	private String serial;
	private String name;
	private String telephone;
	private String content;
	private String dentist;
	private Integer sale;
	private Integer payment;
	private Date dateNext;

	public LoaderCustomerDetailFilter() {
		BeanUtil.initSimplePropertyBean(this);
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Date getDateExcute() {
		return dateExcute;
	}

	public void setDateExcute(Date dateExcute) {
		this.dateExcute = dateExcute;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDentist() {
		return dentist;
	}

	public void setDentist(String dentist) {
		this.dentist = dentist;
	}

	public Integer getSale() {
		return sale;
	}

	public void setSale(Integer sale) {
		this.sale = sale;
	}

	public Integer getPayment() {
		return payment;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
	}

	public Date getDateNext() {
		return dateNext;
	}

	public void setDateNext(Date dateNext) {
		this.dateNext = dateNext;
	}

}
