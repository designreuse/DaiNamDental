package com.datawings.app.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datawings.app.dao.IRecordsDao;
import com.datawings.app.filter.CustomerFiler;
import com.datawings.app.filter.RecordsFilter;
import com.datawings.app.model.Customer;
import com.datawings.app.model.Records;
import com.datawings.app.model.SysUser;
import com.datawings.app.service.IRecordsService;

@Service
public class RecordsService implements IRecordsService{
	@Autowired
	private IRecordsDao dao;
	
	@Transactional
	public Records find(Serializable id) {
		return dao.find(id);
	}

	@Transactional
	public Serializable save(Records model) {
		return dao.save(model);
	}

	@Transactional
	public void update(Records model) {
		dao.update(model);
	}

	@Transactional
	public void merge(Records model) {
		dao.merge(model);
	}

	@Transactional
	public void saveOrUpdate(Records model) {
		dao.saveOrUpdate(model);
	}

	@Transactional
	public void delete(Records model) {
		dao.delete(model);
	}

	@Transactional
	public void deleteById(Serializable id) {
		dao.deleteById(id);
	}

	//@Transactional
	public List<Records> getSchedule(RecordsFilter filter, SysUser sysUser) {
		return dao.getSchedule(filter, sysUser);
	}

	@Transactional
	public Integer getScheduleRowCount(RecordsFilter filter, SysUser sysUser) {
		return dao.getScheduleRowCount(filter, sysUser);
	}

	@Transactional
	public Integer getCountCustomerReality(CustomerFiler filter, SysUser sysUser) {
		return dao.getCountCustomerReality(filter, sysUser);
	}

	//@Transactional
	public List<Records> getCustomerReality(CustomerFiler filter, SysUser sysUser) {
		return dao.getCustomerReality(filter, sysUser);
	}

	@Transactional
	public Integer getTotalCustomerReality(CustomerFiler filter, SysUser sysUser) {
		return dao.getTotalCustomerReality(filter, sysUser);
	}

	//@Transactional
	public List<Records> getScheduleDashboard(RecordsFilter filter, SysUser sysUser, Integer pageNo) {
		return dao.getScheduleDashboard(filter, sysUser, pageNo);
	}

	@Transactional
	public void updateName(Customer customer) {
		dao.updateName(customer);
	}

	@Override
	public Integer getCountScheduleDashboard(RecordsFilter filter, SysUser sysUser) {
		return dao.getCountScheduleDashboard(filter, sysUser);
	}
}
