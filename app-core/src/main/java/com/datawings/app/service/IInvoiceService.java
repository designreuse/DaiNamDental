package com.datawings.app.service;

import java.io.Serializable;
import java.util.List;

import com.datawings.app.filter.InvoiceFilter;
import com.datawings.app.model.Invoice;
import com.datawings.app.model.SysUser;

public interface IInvoiceService {
	
	public Invoice find(Serializable userId);

	public Serializable save(Invoice model);

	public void update(Invoice model);

	public void merge(Invoice model);

	public void saveOrUpdate(Invoice model);

	public void delete(Invoice model);

	public void deleteById(Serializable id);

	public Integer getInvoiceRowCount(InvoiceFilter filter, SysUser sysUser);

	public List<Invoice> getListInvoice(InvoiceFilter filter, SysUser sysUser);

	public List<Invoice> getAll();

}
