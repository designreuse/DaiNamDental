package com.datawings.app.service;

import java.io.Serializable;
import java.util.List;

import com.datawings.app.filter.InvoiceFilter;
import com.datawings.app.model.Invoice;
import com.datawings.app.model.InvoiceDetail;
import com.datawings.app.model.SysUser;

public interface IInvoiceDetailService {
	public InvoiceDetail find(Serializable userId);

	public Serializable save(InvoiceDetail model);

	public void update(InvoiceDetail model);

	public void merge(InvoiceDetail model);

	public void saveOrUpdate(InvoiceDetail model);

	public void delete(InvoiceDetail model);

	public void deleteById(Serializable id);

	public Integer getBillRowCount(InvoiceFilter filter, SysUser sysUser);

	public List<InvoiceDetail> getBills(InvoiceFilter filter, SysUser sysUser);

	public Integer getTotalBills(InvoiceFilter filter, SysUser sysUser);

	public void updateInf(Invoice invoice);
}
