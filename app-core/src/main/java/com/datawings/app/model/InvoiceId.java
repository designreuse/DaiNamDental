package com.datawings.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.datawings.app.common.BeanUtil;

@Embeddable
public class InvoiceId implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "serial")
	private String serial;
	
	@Column(name = "branch")
	private String branch;
	
	public InvoiceId() {
		init();
	}

	private void init() {
		BeanUtil.initSimplePropertyBean(this);
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	
}
