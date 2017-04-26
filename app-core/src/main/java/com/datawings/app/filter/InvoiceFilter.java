package com.datawings.app.filter;

import java.util.Date;

import com.datawings.app.common.DateUtil;

public class InvoiceFilter extends BaseFilter {

	private String serial;
	private String nameCustomer;
	private String telephone;
	private String branch;
	private String dateFrom;
	private String dateTo;
	private String note;
	
	private String serialCreate;
	private String nameCustomerCreate;
	private String addressCreate;
	private String telephoneCreate;
	private String amountCreate;
	private String branchCreate;
	private String dateExcuteCreate;
	private String causeCreate;
	
	private String contentCreate;
	private String grossCreate;
	private String paymentCreate;
	
	private Integer idInvoice;
	private String serialEdit;
	private String branchEdit;
	private String amountEdit;
	private String dateExcuteEdit;
	private String causeEdit;
	
	private String contentEdit;
	private String grossEdit;
	private String paymentEdit;

	public InvoiceFilter() {
		init();
	}

	public void initCreate() {
		this.serialCreate = "";
		this.nameCustomerCreate= "";
		this.addressCreate= "";
		this.telephoneCreate= "";
		this.amountCreate= "";
		this.branchCreate= "";
		this.causeCreate = "";
		this.contentCreate = "";
		this.grossCreate = "";
		this.paymentCreate = "";
		this.dateExcuteCreate = DateUtil.date2String(new Date(), "dd/MM/yyyy");
	}
	
	public void init() {
		super.init();
		this.dateExcuteCreate = DateUtil.date2String(new Date(), "dd/MM/yyyy");
	}

	public Integer getIdInvoice() {
		return idInvoice;
	}

	public void setIdInvoice(Integer idInvoice) {
		this.idInvoice = idInvoice;
	}

	public String getSerialEdit() {
		return serialEdit;
	}

	public void setSerialEdit(String serialEdit) {
		this.serialEdit = serialEdit;
	}

	public String getBranchEdit() {
		return branchEdit;
	}

	public void setBranchEdit(String branchEdit) {
		this.branchEdit = branchEdit;
	}

	public String getAmountEdit() {
		return amountEdit;
	}

	public void setAmountEdit(String amountEdit) {
		this.amountEdit = amountEdit;
	}

	public String getDateExcuteEdit() {
		return dateExcuteEdit;
	}

	public void setDateExcuteEdit(String dateExcuteEdit) {
		this.dateExcuteEdit = dateExcuteEdit;
	}

	public String getCauseEdit() {
		return causeEdit;
	}

	public void setCauseEdit(String causeEdit) {
		this.causeEdit = causeEdit;
	}

	public String getContentEdit() {
		return contentEdit;
	}

	public void setContentEdit(String contentEdit) {
		this.contentEdit = contentEdit;
	}

	public String getGrossEdit() {
		return grossEdit;
	}

	public void setGrossEdit(String grossEdit) {
		this.grossEdit = grossEdit;
	}

	public String getPaymentEdit() {
		return paymentEdit;
	}

	public void setPaymentEdit(String paymentEdit) {
		this.paymentEdit = paymentEdit;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getContentCreate() {
		return contentCreate;
	}

	public void setContentCreate(String contentCreate) {
		this.contentCreate = contentCreate;
	}

	public String getGrossCreate() {
		return grossCreate;
	}

	public void setGrossCreate(String grossCreate) {
		this.grossCreate = grossCreate;
	}

	public String getPaymentCreate() {
		return paymentCreate;
	}

	public void setPaymentCreate(String paymentCreate) {
		this.paymentCreate = paymentCreate;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getCauseCreate() {
		return causeCreate;
	}

	public void setCauseCreate(String causeCreate) {
		this.causeCreate = causeCreate;
	}

	public String getDateExcuteCreate() {
		return dateExcuteCreate;
	}

	public void setDateExcuteCreate(String dateExcuteCreate) {
		this.dateExcuteCreate = dateExcuteCreate;
	}

	public String getSerial() {
		return serial;
	}

	public String getSerialCreate() {
		return serialCreate;
	}

	public void setSerialCreate(String serialCreate) {
		this.serialCreate = serialCreate;
	}

	public String getNameCustomerCreate() {
		return nameCustomerCreate;
	}

	public void setNameCustomerCreate(String nameCustomerCreate) {
		this.nameCustomerCreate = nameCustomerCreate;
	}

	public String getAddressCreate() {
		return addressCreate;
	}

	public void setAddressCreate(String addressCreate) {
		this.addressCreate = addressCreate;
	}

	public String getTelephoneCreate() {
		return telephoneCreate;
	}

	public void setTelephoneCreate(String telephoneCreate) {
		this.telephoneCreate = telephoneCreate;
	}

	public String getAmountCreate() {
		return amountCreate;
	}

	public void setAmountCreate(String amountCreate) {
		this.amountCreate = amountCreate;
	}

	public String getBranchCreate() {
		return branchCreate;
	}

	public void setBranchCreate(String branchCreate) {
		this.branchCreate = branchCreate;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getNameCustomer() {
		return nameCustomer;
	}

	public void setNameCustomer(String nameCustomer) {
		this.nameCustomer = nameCustomer;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
