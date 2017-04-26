package com.datawings.app.dao;

import java.util.List;

import com.datawings.app.filter.InvoiceFilter;
import com.datawings.app.model.Invoice;
import com.datawings.app.model.InvoiceDetail;
import com.datawings.app.model.SysUser;

public interface IInvoiceDetailDao extends IBaseDao<InvoiceDetail, Integer>{

	Integer getBillRowCount(InvoiceFilter filter, SysUser sysUser);

	List<InvoiceDetail> getBills(InvoiceFilter filter, SysUser sysUser);

	Integer getTotalBills(InvoiceFilter filter, SysUser sysUser);

	void updateInf(Invoice invoice);

}
