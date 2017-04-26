package com.datawings.app.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.datawings.app.dao.IBranchesDao;
import com.datawings.app.model.Branches;

@Repository
public class BranchesDao extends BaseDao<Branches, String> implements IBranchesDao{

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Branches> getBranches() {
		Criteria criteria = this.getSession().createCriteria(this.getPersistentClass());
		criteria.addOrder(Order.asc("name"));
		return  criteria.list();	
	}

}
