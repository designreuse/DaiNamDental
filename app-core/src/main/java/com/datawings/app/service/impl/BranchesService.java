package com.datawings.app.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datawings.app.dao.IBranchesDao;
import com.datawings.app.model.Branches;
import com.datawings.app.service.IBranchesService;

@Service
public class BranchesService implements IBranchesService{

	@Autowired
	private IBranchesDao dao;
	
	@Transactional
	public Branches find(Serializable id) {
		return dao.find(id);
	}

	@Transactional
	public Serializable save(Branches model) {
		return dao.save(model);
	}

	@Transactional
	public void update(Branches model) {
		dao.update(model);
	}

	@Transactional
	public void merge(Branches model) {
		dao.merge(model);
	}

	@Transactional
	public void saveOrUpdate(Branches model) {
		dao.saveOrUpdate(model);
	}

	@Transactional
	public void delete(Branches model) {
		dao.delete(model);
	}

	@Transactional
	public void deleteById(Serializable id) {
		dao.deleteById(id);
	}
	
	@Transactional
	public List<Branches> getBranches() {
		return dao.getBranches();
	}

}
