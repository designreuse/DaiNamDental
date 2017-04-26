package com.datawings.app.dao;

import java.util.List;

import com.datawings.app.filter.DentistFilter;
import com.datawings.app.model.Dentist;
import com.datawings.app.model.SysUser;

public interface IDentistDao extends IBaseDao<Dentist, Integer>{

	Integer getDentistRowCount(DentistFilter filter);

	List<Dentist> getDentist(DentistFilter filter);

	Dentist findByName(DentistFilter filter);

	List<Dentist> findSysUser(SysUser sysUser);

}
