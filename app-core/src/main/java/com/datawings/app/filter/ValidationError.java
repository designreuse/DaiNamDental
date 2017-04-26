package com.datawings.app.filter;

import com.datawings.app.common.BeanUtil;

public class ValidationError {
	private String propertyName;
	private String message;

	public ValidationError() {
		this.init();
	}

	public void init() {
		BeanUtil.initSimplePropertyBean(this);
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}