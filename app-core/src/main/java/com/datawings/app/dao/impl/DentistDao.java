package com.datawings.app.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.datawings.app.dao.IDentistDao;
import com.datawings.app.filter.DentistFilter;
import com.datawings.app.model.Dentist;
import com.datawings.app.model.SysUser;

@Repository
public class DentistDao extends BaseDao<Dentist, Integer> implements IDentistDao{

	@SuppressWarnings("deprecation")
	public Integer getDentistRowCount(DentistFilter filter) {
		Criteria crit = this.getSession().createCriteria(this.getPersistentClass());
		if(StringUtils.isNotBlank(filter.getName())){
			crit.add(Restrictions.like("name", filter.getName().trim().toUpperCase(), MatchMode.ANYWHERE).ignoreCase());
		}
		if(StringUtils.isNotBlank(filter.getBranch())){
			crit.add(Restrictions.eq("branch", filter.getBranch().trim()));
		}
		return ((Number) crit.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Dentist> getDentist(DentistFilter filter) {
		Criteria criteria = this.getSession().createCriteria(this.getPersistentClass());
		if(StringUtils.isNotBlank(filter.getName())){
			criteria.add(Restrictions.like("name", filter.getName().trim().toUpperCase(), MatchMode.ANYWHERE).ignoreCase());
		}
		if(StringUtils.isNotBlank(filter.getBranch())){
			criteria.add(Restrictions.eq("branch", filter.getBranch().trim()));
		}
		
		criteria.addOrder(Order.asc("branch"));
		criteria.addOrder(Order.asc("name"));
		criteria.setMaxResults(filter.getPageSize());
		criteria.setFirstResult(filter.getOffset());
		List<Dentist> result = criteria.list();		
		return result;
	}

	@SuppressWarnings("deprecation")
	public Dentist findByName(DentistFilter filter) {
		Criteria crit = this.getSession().createCriteria(this.getPersistentClass());
		crit.add(Restrictions.like("name", filter.getNameCreate().trim().toUpperCase(), MatchMode.ANYWHERE).ignoreCase());
		crit.add(Restrictions.eq("branch", filter.getBranchCreate().trim()));
		
		return (Dentist) crit.uniqueResult();
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Dentist> findSysUser(SysUser sysUser) {
		Criteria crit = this.getSession().createCriteria(this.getPersistentClass());
		crit.add(Restrictions.eq("branch", sysUser.getBranch().trim()));
		
		crit.addOrder(Order.asc("name"));
		List<Dentist> result = crit.list();		
		return result;
	}

}
