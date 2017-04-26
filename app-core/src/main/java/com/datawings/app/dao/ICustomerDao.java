package com.datawings.app.dao;

import java.util.List;

import com.datawings.app.filter.CustomerFiler;
import com.datawings.app.model.Customer;
import com.datawings.app.model.CustomerId;
import com.datawings.app.model.SysUser;

public interface ICustomerDao extends IBaseDao<Customer, CustomerId>{

	Integer getCustomerRowCount(CustomerFiler filter, SysUser sysUser);

	List<Customer> getCustomers(CustomerFiler filter, SysUser sysUser);

	Customer findByUser(String id, SysUser sysUser);

	List<Customer> getCustomer(String branch);

}
