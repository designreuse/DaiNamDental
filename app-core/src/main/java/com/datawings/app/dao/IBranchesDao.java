package com.datawings.app.dao;

import java.util.List;

import com.datawings.app.model.Branches;

public interface IBranchesDao extends IBaseDao<Branches, String>{

	List<Branches> getBranches();

}
