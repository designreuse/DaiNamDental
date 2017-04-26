package com.datawings.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.datawings.app.common.BeanUtil;

@Entity
@Table(name = "records")
public class Records extends Base {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue()
	@Column(name = "record_id", unique = true, nullable = false)
	private Integer recordId;

	@Column(name = "serial")
	private String serial;
	
	@Column(name = "branch")
	private String branch;
	
	@Column(name = "date_excute")
	private Date dateExcute;

	@Column(name = "teeth")
	private String teeth;

	@Column(name = "content")
	private String content;

	@Column(name = "dentist")
	private String dentist;

	@Column(name = "date_next")
	private Date dateNext;

	@Column(name = "content_next")
	private String contentNext;

	@Column(name = "note")
	private String note;

	@Column(name = "gross")
	private Integer gross;

	@Column(name = "sale")
	private Integer sale;

	@Column(name = "payment")
	private Integer payment;

	@Column(name = "created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date")
	private Date modifiedDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="serial", referencedColumnName="serial", nullable = false, insertable = false, updatable = false),
		@JoinColumn(name="branch", referencedColumnName="branch", nullable = false, insertable = false, updatable = false)
	}) 
	private Customer customer;

	public Records() {
		init();
	}

	private void init() {
		BeanUtil.initSimplePropertyBean(this);
	}

	public Customer getCustomer() {
		return customer;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public Date getDateExcute() {
		return dateExcute;
	}

	public void setDateExcute(Date dateExcute) {
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

	public Date getDateNext() {
		return dateNext;
	}

	public void setDateNext(Date dateNext) {
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

	public Integer getGross() {
		return gross;
	}

	public void setGross(Integer gross) {
		this.gross = gross;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}


}
