package com.datawings.app.service;

import java.io.Serializable;
import java.util.List;

import com.datawings.app.filter.UserFilter;
import com.datawings.app.model.SysUser;

public interface ISysUserService {

	public Integer getRowsCount();

	public Integer getIdMax(String propertyName);

	public List<SysUser> findAll();

	public SysUser find(Serializable userId);

	public Serializable save(SysUser model);

	public void update(SysUser model);

	public void merge(SysUser model);

	public void saveOrUpdate(SysUser model);

	public void delete(SysUser model);

	public void deleteById(Serializable id);

	public List<SysUser> getUser(UserFilter userFilter);

}
