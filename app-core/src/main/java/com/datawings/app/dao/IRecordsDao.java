package com.datawings.app.dao;

import java.util.List;

import com.datawings.app.filter.CustomerFiler;
import com.datawings.app.filter.RecordsFilter;
import com.datawings.app.model.Customer;
import com.datawings.app.model.Records;
import com.datawings.app.model.SysUser;

public interface IRecordsDao extends IBaseDao<Records, Integer>{

	List<Records> getSchedule(RecordsFilter filter, SysUser sysUser);

	Integer getScheduleRowCount(RecordsFilter filter, SysUser sysUser);

	Integer getCountCustomerReality(CustomerFiler filter, SysUser sysUser);

	List<Records> getCustomerReality(CustomerFiler filter, SysUser sysUser);

	Integer getTotalCustomerReality(CustomerFiler filter, SysUser sysUser);

	List<Records> getScheduleDashboard(RecordsFilter filter, SysUser sysUser, Integer pageNo);

	void updateName(Customer customer);

	Integer getCountScheduleDashboard(RecordsFilter filter, SysUser sysUser);

}
