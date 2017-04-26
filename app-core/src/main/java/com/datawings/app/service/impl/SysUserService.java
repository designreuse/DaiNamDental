package com.datawings.app.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datawings.app.dao.ISysUserDao;
import com.datawings.app.filter.UserFilter;
import com.datawings.app.model.SysUser;
import com.datawings.app.service.ISysUserService;

@Service("sysUserService")
public class SysUserService implements ISysUserService {

	@Autowired
	private ISysUserDao dao;

	@Transactional
	public Integer getRowsCount() {
		return dao.getRowsCount();
	}

	@Transactional
	public Integer getIdMax(String propertyName) {
		return dao.getIdMax(propertyName);
	}

	@Transactional
	public List<SysUser> findAll() {
		return dao.findAll();
	}

	@Transactional
	public SysUser find(Serializable id) {
		return dao.find(id);
	}

	@Transactional
	public Serializable save(SysUser model) {
		return dao.save(model);
	}

	@Transactional
	public void update(SysUser model) {
		dao.update(model);
	}

	@Transactional
	public void merge(SysUser model) {
		dao.merge(model);
	}

	@Transactional
	public void saveOrUpdate(SysUser model) {
		dao.saveOrUpdate(model);
	}

	@Transactional
	public void delete(SysUser model) {
		dao.delete(model);
	}

	@Transactional
	public void deleteById(Serializable id) {
		dao.deleteById(id);
	}

	@Transactional
	public List<SysUser> getUser(UserFilter userFilter) {
		return dao.getUser(userFilter);
	}

	@Transactional
	public SysUser findByUsernameLogin(String username) {
		return dao.findByUsernameLogin(username);
	}

}
