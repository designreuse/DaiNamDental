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
@Table(name = "invoice_detail")
public class InvoiceDetail extends Base {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue()
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "serial")
	private String serial;
	
	@Column(name = "branch")
	private String branch;
	
	@Column(name = "date_excute")
	private Date dateExcute;

	@Column(name = "cause")
	private String cause;

	@Column(name = "amount")
	private Integer amount;

	@Column(name = "content")
	private String content;

	@Column(name = "gross")
	private Integer gross;

	@Column(name = "payment")
	private Integer payment;
	
	@Column(name = "note")
	private String note;

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
	private Invoice invoice;

	public InvoiceDetail() {
		init();
	}

	private void init() {
		BeanUtil.initSimplePropertyBean(this);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getSerial() {
		return serial;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public Date getDateExcute() {
		return dateExcute;
	}

	public void setDateExcute(Date dateExcute) {
		this.dateExcute = dateExcute;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getGross() {
		return gross;
	}

	public void setGross(Integer gross) {
		this.gross = gross;
	}

	public Integer getPayment() {
		return payment;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
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

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

}
