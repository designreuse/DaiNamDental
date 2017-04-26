package com.datawings.app.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datawings.app.dao.IInvoiceDetailDao;
import com.datawings.app.filter.InvoiceFilter;
import com.datawings.app.model.Invoice;
import com.datawings.app.model.InvoiceDetail;
import com.datawings.app.model.SysUser;
import com.datawings.app.service.IInvoiceDetailService;

@Service
public class InvoiceDetailService implements IInvoiceDetailService{

	@Autowired
	private IInvoiceDetailDao dao;
	
	public InvoiceDetail find(Serializable id) {
		return dao.find(id);
	}

	@Transactional
	public Serializable save(InvoiceDetail model) {
		return dao.save(model);
	}

	@Transactional
	public void update(InvoiceDetail model) {
		dao.update(model);
	}

	@Transactional
	public void merge(InvoiceDetail model) {
		dao.merge(model);
	}

	@Transactional
	public void saveOrUpdate(InvoiceDetail model) {
		dao.saveOrUpdate(model);
	}

	@Transactional
	public void delete(InvoiceDetail model) {
		dao.delete(model);
	}

	@Transactional
	public void deleteById(Serializable id) {
		dao.deleteById(id);
	}

	@Transactional
	public Integer getBillRowCount(InvoiceFilter filter, SysUser sysUser) {
		return dao.getBillRowCount(filter, sysUser);
	}

	//@Transactional
	public List<InvoiceDetail> getBills(InvoiceFilter filter, SysUser sysUser) {
		return dao.getBills(filter, sysUser);
	}

	@Transactional
	public Integer getTotalBills(InvoiceFilter filter, SysUser sysUser) {
		return dao.getTotalBills(filter, sysUser);
	}

	@Transactional
	public void updateInf(Invoice invoice) {
		dao.updateInf(invoice);
	}

}
