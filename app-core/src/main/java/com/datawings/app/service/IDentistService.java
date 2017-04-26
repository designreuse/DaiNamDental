package com.datawings.app.service;

import java.io.Serializable;
import java.util.List;

import com.datawings.app.filter.DentistFilter;
import com.datawings.app.model.Dentist;
import com.datawings.app.model.SysUser;

public interface IDentistService {

	public Dentist find(Serializable userId);

	public Serializable save(Dentist model);

	public void update(Dentist model);

	public void merge(Dentist model);

	public void saveOrUpdate(Dentist model);

	public void delete(Dentist model);

	public void deleteById(Serializable id);
	
	Integer getDentistRowCount(DentistFilter filter);

	List<Dentist> getDentist(DentistFilter filter);

	public Dentist findByName(DentistFilter filter);

	public List<Dentist> findSysUser(SysUser sysUser);

}
