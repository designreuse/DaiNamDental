package com.datawings.app.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datawings.app.dao.IDentistDao;
import com.datawings.app.filter.DentistFilter;
import com.datawings.app.model.Dentist;
import com.datawings.app.model.SysUser;
import com.datawings.app.service.IDentistService;

@Service
public class DentistService implements IDentistService{

	@Autowired
	private IDentistDao dao;
	
	@Transactional
	public Dentist find(Serializable id) {
		return dao.find(id);
	}

	@Transactional
	public Serializable save(Dentist model) {
		return dao.save(model);
	}

	@Transactional
	public void update(Dentist model) {
		dao.update(model);
	}

	@Transactional
	public void merge(Dentist model) {
		dao.merge(model);
	}

	@Transactional
	public void saveOrUpdate(Dentist model) {
		dao.saveOrUpdate(model);
	}

	@Transactional
	public void delete(Dentist model) {
		dao.delete(model);
	}

	@Transactional
	public void deleteById(Serializable id) {
		dao.deleteById(id);
	}
	
	@Transactional
	public Integer getDentistRowCount(DentistFilter filter) {
		return dao.getDentistRowCount(filter);
	}

	@Transactional
	public List<Dentist> getDentist(DentistFilter filter) {
		return dao.getDentist(filter) ;
	}

	@Transactional
	public Dentist findByName(DentistFilter filter) {
		return dao.findByName(filter);
	}

	@Transactional
	public List<Dentist> findSysUser(SysUser sysUser) {
		return dao.findSysUser(sysUser);
	}

}
