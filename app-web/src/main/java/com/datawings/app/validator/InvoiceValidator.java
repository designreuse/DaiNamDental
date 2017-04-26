package com.datawings.app.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.datawings.app.common.DateUtil;
import com.datawings.app.common.IntegerUtil;
import com.datawings.app.common.StringUtilz;
import com.datawings.app.filter.InvoiceFilter;
import com.datawings.app.model.Invoice;
import com.datawings.app.model.InvoiceId;
import com.datawings.app.model.SysUser;
import com.datawings.app.service.IInvoiceService;

@Component
public class InvoiceValidator {

	@Autowired
	private IInvoiceService invoiceService;
	
	public int checkInvoiceAdd(Object target, SysUser sysUser, Errors errors) {
		InvoiceFilter filter = (InvoiceFilter) target;
		Integer erroCode = 0;
		
		if (StringUtils.isBlank(filter.getSerialCreate())) {
			errors.rejectValue("serialCreate", "message.empty", "!");
			erroCode = 1;
		}else {
			InvoiceId invoiceId = new InvoiceId();
			invoiceId.setSerial(filter.getSerialCreate());
			invoiceId.setBranch(sysUser.getBranch());
			
			Invoice invoice = invoiceService.find(invoiceId);
			if(invoice != null){
				errors.rejectValue("serialCreate", "message.exist", "!");
				erroCode = 1;
				return erroCode;
			}
		}
		
		if (StringUtils.isBlank(filter.getNameCustomerCreate())) {
			errors.rejectValue("nameCustomerCreate", "message.empty", "!");
			erroCode = 1;
		}
		
		if (StringUtils.isBlank(filter.getContentCreate())) {
			errors.rejectValue("contentCreate", "message.empty", "!");
			erroCode = 1;
		}
		
		if (StringUtils.isBlank(filter.getGrossCreate())) {
			errors.rejectValue("grossCreate", "message.empty", "!");
			erroCode = 1;
		}else if (!IntegerUtil.isInteger(StringUtilz.replaceMoney(filter.getGrossCreate()))) {
			errors.rejectValue("grossCreate", "message.valid", "!");
			erroCode = 1;
		}
		
		if (StringUtils.isBlank(filter.getPaymentCreate())) {
			errors.rejectValue("paymentCreate", "message.empty", "!");
			erroCode = 1;
		}else if (!IntegerUtil.isInteger(StringUtilz.replaceMoney(filter.getPaymentCreate()))) {
			errors.rejectValue("paymentCreate", "message.valid", "!");
			erroCode = 1;
		}
		
		if (StringUtils.isBlank(filter.getDateExcuteCreate())) {
			errors.rejectValue("dateExcuteCreate", "message.empty", "!");
			erroCode = 1;
		}else if (!DateUtil.checkDateAsString(filter.getDateExcuteCreate(), "dd/MM/yyyy")) {
			errors.rejectValue("dateExcuteCreate", "message.valid", "!");
			erroCode = 1;
		}
		
		if (StringUtils.isBlank(filter.getCauseCreate())) {
			errors.rejectValue("causeCreate", "message.empty", "!");
			erroCode = 1;
		}
		
		if (StringUtils.isBlank(filter.getAmountCreate())) {
			errors.rejectValue("amountCreate", "message.empty", "!");
			erroCode = 1;
		}else if (!IntegerUtil.isInteger(StringUtilz.replaceMoney(filter.getAmountCreate()))) {
			errors.rejectValue("amountCreate", "message.valid", "!");
			erroCode = 1;
		}
		
		return erroCode;
	}

	public int checkInvoiceDetailAdd(Object target, Errors errors) {
		InvoiceFilter filter = (InvoiceFilter) target;
		Integer erroCode = 0;
		
		if (StringUtils.isBlank(filter.getDateExcuteCreate())) {
			errors.rejectValue("dateExcuteCreate", "message.empty", "!");
			erroCode = 1;
		}else if (!DateUtil.checkDateAsString(filter.getDateExcuteCreate(), "dd/MM/yyyy")) {
			errors.rejectValue("dateExcuteCreate", "message.valid", "!");
			erroCode = 1;
		}
		
		if (StringUtils.isBlank(filter.getCauseCreate())) {
			errors.rejectValue("causeCreate", "message.empty", "!");
			erroCode = 1;
		}
		
		if (StringUtils.isBlank(filter.getAmountCreate())) {
			errors.rejectValue("amountCreate", "message.empty", "!");
			erroCode = 1;
		}else if (!IntegerUtil.isInteger(StringUtilz.replaceMoney(filter.getAmountCreate()))) {
			errors.rejectValue("amountCreate", "message.valid", "!");
			erroCode = 1;
		}
		
		if (StringUtils.isBlank(filter.getContentCreate())) {
			errors.rejectValue("contentCreate", "message.empty", "!");
			erroCode = 1;
		}
		
		if (StringUtils.isBlank(filter.getGrossCreate())) {
			errors.rejectValue("grossCreate", "message.empty", "!");
			erroCode = 1;
		}else if (!IntegerUtil.isInteger(StringUtilz.replaceMoney(filter.getGrossCreate()))) {
			errors.rejectValue("grossCreate", "message.valid", "!");
			erroCode = 1;
		}
		
		if (StringUtils.isBlank(filter.getPaymentCreate())) {
			errors.rejectValue("paymentCreate", "message.empty", "!");
			erroCode = 1;
		}else if (!IntegerUtil.isInteger(StringUtilz.replaceMoney(filter.getPaymentCreate()))) {
			errors.rejectValue("paymentCreate", "message.valid", "!");
			erroCode = 1;
		}
		
		return erroCode;
	}

	public int checkInvoiceDetailEdit(Object target, Errors errors) {
		InvoiceFilter filter = (InvoiceFilter) target;
		Integer erroCode = 0;
		
		if (StringUtils.isBlank(filter.getDateExcuteEdit())) {
			errors.rejectValue("dateExcuteEdit", "message.empty", "!");
			erroCode = 1;
		}else if (!DateUtil.checkDateAsString(filter.getDateExcuteEdit(), "dd/MM/yyyy")) {
			errors.rejectValue("dateExcuteEdit", "message.valid", "!");
			erroCode = 1;
		}
		
		if (StringUtils.isBlank(filter.getCauseEdit())) {
			errors.rejectValue("causeEdit", "message.empty", "!");
			erroCode = 1;
		}
		
		if (StringUtils.isBlank(filter.getAmountEdit())) {
			errors.rejectValue("amountEdit", "message.empty", "!");
			erroCode = 1;
		}else if (!IntegerUtil.isInteger(StringUtilz.replaceMoney(filter.getAmountEdit()))) {
			errors.rejectValue("amountEdit", "message.valid", "!");
			erroCode = 1;
		}
		
		if (StringUtils.isBlank(filter.getContentEdit())) {
			errors.rejectValue("contentEdit", "message.empty", "!");
			erroCode = 1;
		}
		
		if (StringUtils.isBlank(filter.getGrossEdit())) {
			errors.rejectValue("grossEdit", "message.empty", "!");
			erroCode = 1;
		}else if (!IntegerUtil.isInteger(StringUtilz.replaceMoney(filter.getGrossEdit()))) {
			errors.rejectValue("grossEdit", "message.valid", "!");
			erroCode = 1;
		}
		
		if (StringUtils.isBlank(filter.getPaymentEdit())) {
			errors.rejectValue("paymentEdit", "message.empty", "!");
			erroCode = 1;
		}else if (!IntegerUtil.isInteger(StringUtilz.replaceMoney(filter.getPaymentEdit()))) {
			errors.rejectValue("paymentEdit", "message.valid", "!");
			erroCode = 1;
		}
		
		return erroCode;
	}

}
