package com.datawings.app.service;

import java.io.Serializable;
import java.util.List;

import com.datawings.app.filter.CustomerFiler;
import com.datawings.app.filter.RecordsFilter;
import com.datawings.app.model.Customer;
import com.datawings.app.model.Records;
import com.datawings.app.model.SysUser;

public interface IRecordsService {
	
	public Records find(Serializable userId);

	public Serializable save(Records model);

	public void update(Records model);

	public void merge(Records model);

	public void saveOrUpdate(Records model);

	public void delete(Records model);

	public void deleteById(Serializable id);

	public List<Records> getSchedule(RecordsFilter filter, SysUser sysUser);

	public Integer getScheduleRowCount(RecordsFilter filter, SysUser sysUser);

	public Integer getCountCustomerReality(CustomerFiler filter, SysUser sysUser);

	public List<Records> getCustomerReality(CustomerFiler filter,SysUser sysUser);

	public Integer getTotalCustomerReality(CustomerFiler filter, SysUser sysUser);

	public List<Records> getScheduleDashboard(RecordsFilter filter, SysUser sysUser, Integer pageNo);

	public void updateName(Customer elm);

	public Integer getCountScheduleDashboard(RecordsFilter filter, SysUser sysUser);
}
