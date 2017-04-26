package com.datawings.app.filter;

import java.util.Date;

import com.datawings.app.common.DateUtil;

public class RecordsFilter extends BaseFilter{
	
	private String serial;
	private String name;
	private String telephone;
	private String dateExcute;
	private String teeth;
	private String content;
	private String dentist;
	private String dateNext;
	private String dateNextFrom;
	private String dateNextTo;
	private String contentNext;
	private String note;
	private String gross;
	private String sale;
	private String payment;
	private String branch;
	
	private String serialEdit;
	private String branchEdit;
	private Integer recordIdEdit;
	private String dateExcuteEdit;
	private String teethEdit;
	private String contentEdit;
	private String dentistEdit;
	private String dateNextEdit;
	private String contentNextEdit;
	private String grossEdit;
	private String saleEdit;
	private String paymentEdit;
	

	public RecordsFilter() {
		init();
	}

	public void init() {
		super.init();
		this.sale = "0";
		this.dateExcute = DateUtil.date2String(new Date(), "dd/MM/yyyy");
	}

	public Integer getRecordIdEdit() {
		return recordIdEdit;
	}

	public String getSerialEdit() {
		return serialEdit;
	}

	public String getBranchEdit() {
		return branchEdit;
	}

	public void setBranchEdit(String branchEdit) {
		this.branchEdit = branchEdit;
	}

	public void setSerialEdit(String serialEdit) {
		this.serialEdit = serialEdit;
	}

	public void setRecordIdEdit(Integer recordIdEdit) {
		this.recordIdEdit = recordIdEdit;
	}

	public String getDateExcuteEdit() {
		return dateExcuteEdit;
	}

	public void setDateExcuteEdit(String dateExcuteEdit) {
		this.dateExcuteEdit = dateExcuteEdit;
	}

	public String getTeethEdit() {
		return teethEdit;
	}

	public void setTeethEdit(String teethEdit) {
		this.teethEdit = teethEdit;
	}

	public String getContentEdit() {
		return contentEdit;
	}

	public void setContentEdit(String contentEdit) {
		this.contentEdit = contentEdit;
	}

	public String getDentistEdit() {
		return dentistEdit;
	}

	public void setDentistEdit(String dentistEdit) {
		this.dentistEdit = dentistEdit;
	}

	public String getDateNextEdit() {
		return dateNextEdit;
	}

	public void setDateNextEdit(String dateNextEdit) {
		this.dateNextEdit = dateNextEdit;
	}

	public String getContentNextEdit() {
		return contentNextEdit;
	}

	public void setContentNextEdit(String contentNextEdit) {
		this.contentNextEdit = contentNextEdit;
	}

	public String getGrossEdit() {
		return grossEdit;
	}

	public void setGrossEdit(String grossEdit) {
		this.grossEdit = grossEdit;
	}

	public String getSaleEdit() {
		return saleEdit;
	}

	public void setSaleEdit(String saleEdit) {
		this.saleEdit = saleEdit;
	}

	public String getPaymentEdit() {
		return paymentEdit;
	}

	public void setPaymentEdit(String paymentEdit) {
		this.paymentEdit = paymentEdit;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDateExcute() {
		return dateExcute;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getDateNextFrom() {
		return dateNextFrom;
	}

	public void setDateNextFrom(String dateNextFrom) {
		this.dateNextFrom = dateNextFrom;
	}

	public String getDateNextTo() {
		return dateNextTo;
	}

	public void setDateNextTo(String dateNextTo) {
		this.dateNextTo = dateNextTo;
	}

	public void setDateExcute(String dateExcute) {
		this.dateExcute = dateExcute;
	}

	public String getTeeth() {
		return teeth;
	}

	public void setTeeth(String teeth) {
		this.teeth = teeth;
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

	public String getDateNext() {
		return dateNext;
	}

	public void setDateNext(String dateNext) {
		this.dateNext = dateNext;
	}

	public String getContentNext() {
		return contentNext;
	}

	public void setContentNext(String contentNext) {
		this.contentNext = contentNext;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getGross() {
		return gross;
	}

	public void setGross(String gross) {
		this.gross = gross;
	}

	public String getSale() {
		return sale;
	}

	public void setSale(String sale) {
		this.sale = sale;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

}
