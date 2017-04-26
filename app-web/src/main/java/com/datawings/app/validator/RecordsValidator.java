package com.datawings.app.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.datawings.app.common.DateUtil;
import com.datawings.app.common.IntegerUtil;
import com.datawings.app.common.StringUtilz;
import com.datawings.app.filter.RecordsFilter;

@Component
public class RecordsValidator {

	public int checkRecordsAdd(Object target, Errors errors) {
		RecordsFilter filter = (RecordsFilter) target;
		Integer erroCode = 0;
		
		if (StringUtils.isBlank(filter.getDateExcute())) {
			errors.rejectValue("dateExcute", "message.empty", "!");
			erroCode = 1;
		}else if (!DateUtil.checkDateAsString(filter.getDateExcute(), "dd/MM/yyyy")) {
			errors.rejectValue("dateExcute", "message.valid", "!");
			erroCode = 1;
		}
		
		if (StringUtils.isBlank(filter.getTeeth())) {
			errors.rejectValue("teeth", "message.empty", "!");
			erroCode = 1;
		}
		
		if (StringUtils.isBlank(filter.getContent())) {
			errors.rejectValue("content", "message.empty", "!");
			erroCode = 1;
		}
		
		if (StringUtils.isBlank(filter.getGross())) {
			errors.rejectValue("gross", "message.empty", "!");
			erroCode = 1;
		}else if (StringUtils.isNotBlank(filter.getGross()) && !IntegerUtil.isInteger(StringUtilz.replaceMoney(filter.getGross()))) {
			errors.rejectValue("gross", "message.valid", "!");
			erroCode = 1;
		}
		
		if (StringUtils.isBlank(filter.getSale())) {
			errors.rejectValue("sale", "message.empty", "!");
			erroCode = 1;
		}else if (StringUtils.isNotBlank(filter.getSale()) && !IntegerUtil.isInteger(StringUtilz.replaceMoney(filter.getSale()))) {
			errors.rejectValue("sale", "message.valid", "!");
			erroCode = 1;
		}
		
		if (StringUtils.isBlank(filter.getPayment())) {
			errors.rejectValue("payment", "message.empty", "!");
			erroCode = 1;
		} else if (StringUtils.isNotBlank(filter.getPayment()) && !IntegerUtil.isInteger(StringUtilz.replaceMoney(filter.getPayment()))) {
			errors.rejectValue("payment", "message.valid", "!");
			erroCode = 1;
		}
		
		if (StringUtils.isNotBlank(filter.getDateNext()) && !DateUtil.checkDateAsString(filter.getDateNext(), "dd/MM/yyyy")) {
			errors.rejectValue("dateNext", "message.valid", "!");
			erroCode = 1;
		}
		
		return erroCode;
	}

	public int checkRecordsEdit(Object target, Errors errors) {
		RecordsFilter filter = (RecordsFilter) target;
		Integer erroCode = 0;
		
		if (StringUtils.isBlank(filter.getDateExcuteEdit())) {
			errors.rejectValue("dateExcuteEdit", "message.empty", "!");
			erroCode = 1;
		}else if (!DateUtil.checkDateAsString(filter.getDateExcuteEdit(), "dd/MM/yyyy")) {
			errors.rejectValue("dateExcuteEdit", "message.valid", "!");
			erroCode = 1;
		}
		
		if (StringUtils.isBlank(filter.getTeethEdit())) {
			errors.rejectValue("teethEdit", "message.empty", "!");
			erroCode = 1;
		}
		
		if (StringUtils.isBlank(filter.getContentEdit())) {
			errors.rejectValue("contentEdit", "message.empty", "!");
			erroCode = 1;
		}
		
		if (StringUtils.isBlank(filter.getGrossEdit())) {
			errors.rejectValue("grossEdit", "message.empty", "!");
			erroCode = 1;
		}else if (StringUtils.isNotBlank(filter.getGrossEdit()) && !IntegerUtil.isInteger(StringUtilz.replaceMoney(filter.getGrossEdit()))) {
			errors.rejectValue("grossEdit", "message.valid", "!");
			erroCode = 1;
		}
		
		if (StringUtils.isBlank(filter.getSaleEdit())) {
			errors.rejectValue("saleEdit", "message.empty", "!");
			erroCode = 1;
		}else if (StringUtils.isNotBlank(filter.getSaleEdit()) && !IntegerUtil.isInteger(StringUtilz.replaceMoney(filter.getSaleEdit()))) {
			errors.rejectValue("saleEdit", "message.valid", "!");
			erroCode = 1;
		}
		
		if (StringUtils.isBlank(filter.getPaymentEdit())) {
			errors.rejectValue("paymentEdit", "message.empty", "!");
			erroCode = 1;
		} else if (StringUtils.isNotBlank(filter.getPaymentEdit()) && !IntegerUtil.isInteger(StringUtilz.replaceMoney(filter.getPaymentEdit()))) {
			errors.rejectValue("paymentEdit", "message.valid", "!");
			erroCode = 1;
		}
		
		if (StringUtils.isNotBlank(filter.getDateNextEdit()) && !DateUtil.checkDateAsString(filter.getDateNextEdit(), "dd/MM/yyyy")) {
			errors.rejectValue("dateNextEdit", "message.valid", "!");
			erroCode = 1;
		}
		
		return erroCode;
	}
}
