package com.datawings.app.dao;

import java.util.List;

import com.datawings.app.filter.InvoiceFilter;
import com.datawings.app.model.Invoice;
import com.datawings.app.model.InvoiceId;
import com.datawings.app.model.SysUser;

public interface IInvoiceDao extends IBaseDao<Invoice, InvoiceId>{

	Integer getInvoiceRowCount(InvoiceFilter filter, SysUser sysUser);

	List<Invoice> getListInvoice(InvoiceFilter filter, SysUser sysUser);

}
