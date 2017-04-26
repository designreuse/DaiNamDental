package com.datawings.app.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datawings.app.dao.IInvoiceDao;
import com.datawings.app.filter.InvoiceFilter;
import com.datawings.app.model.Invoice;
import com.datawings.app.model.SysUser;
import com.datawings.app.service.IInvoiceService;

@Service
public class InvoiceService implements IInvoiceService{
	@Autowired
	private IInvoiceDao dao;
	
	public Invoice find(Serializable id) {
		return dao.find(id);
	}

	@Transactional
	public Serializable save(Invoice model) {
		return dao.save(model);
	}

	@Transactional
	public void update(Invoice model) {
		dao.update(model);
	}

	@Transactional
	public void merge(Invoice model) {
		dao.merge(model);
	}

	@Transactional
	public void saveOrUpdate(Invoice model) {
		dao.saveOrUpdate(model);
	}

	@Transactional
	public void delete(Invoice model) {
		dao.delete(model);
	}

	@Transactional
	public void deleteById(Serializable id) {
		dao.deleteById(id);
	}

	@Transactional
	public Integer getInvoiceRowCount(InvoiceFilter filter, SysUser sysUser) {
		return dao.getInvoiceRowCount(filter, sysUser);
	}

	@Transactional
	public List<Invoice> getListInvoice(InvoiceFilter filter, SysUser sysUser) {
		return dao.getListInvoice(filter, sysUser);
	}

	@Transactional
	public List<Invoice> getAll() {
		return dao.findAll();
	}
}
