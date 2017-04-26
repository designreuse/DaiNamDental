package com.datawings.app.service;

import java.io.Serializable;
import java.util.List;

import com.datawings.app.model.Branches;

public interface IBranchesService {

	public Branches find(Serializable userId);

	public Serializable save(Branches model);

	public void update(Branches model);

	public void merge(Branches model);

	public void saveOrUpdate(Branches model);

	public void delete(Branches model);

	public void deleteById(Serializable id);
	
	public List<Branches> getBranches();

}
