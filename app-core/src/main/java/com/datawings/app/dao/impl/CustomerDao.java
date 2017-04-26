package com.datawings.app.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.datawings.app.common.SqlUtil;
import com.datawings.app.dao.ICustomerDao;
import com.datawings.app.filter.CustomerFiler;
import com.datawings.app.model.Customer;
import com.datawings.app.model.CustomerId;
import com.datawings.app.model.SysUser;

@Repository
public class CustomerDao extends BaseDao<Customer, CustomerId> implements ICustomerDao{

	@SuppressWarnings("deprecation")
	public Integer getCustomerRowCount(CustomerFiler filter, SysUser sysUser) {
		Criteria criteria = this.getSession().createCriteria(this.getPersistentClass());
		
		if(StringUtils.equals(sysUser.getRole(), "USER")){
			criteria.add(Restrictions.eq("id.branch", sysUser.getBranch()));
		}else {
			if(StringUtils.isNotBlank(filter.getBranch())){
				criteria.add(Restrictions.eq("id.branch", filter.getBranch()));
			}
		}
		
		if(StringUtils.isNotBlank(filter.getSerial())){
			criteria.add(Restrictions.eq("id.serial", filter.getSerial().trim()));
		}
		if(StringUtils.isNotBlank(filter.getName())){
			criteria.add(Restrictions.like("name", filter.getName().trim().toUpperCase(), MatchMode.ANYWHERE));
		}
		if(StringUtils.isNotBlank(filter.getTelephone())){
			criteria.add(Restrictions.eq("telephone", filter.getTelephone().trim()));
		}
		if(StringUtils.isNotBlank(filter.getStatus())){
			criteria.add(Restrictions.eq("status", filter.getStatus().trim()));
		}
		if(StringUtils.isNotBlank(filter.getType())){
			criteria.add(Restrictions.eq("type", filter.getType().trim()));
		}
		
		SqlUtil.buildDatesBetweenLikeCrit("date_start", filter.getDateStartFrom(), filter.getDateStartTo(), criteria);
		
		return ((Number) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Customer> getCustomers(CustomerFiler filter, SysUser sysUser) {
		Criteria criteria = this.getSession().createCriteria(this.getPersistentClass());
		if(StringUtils.equals(sysUser.getRole(), "USER")){
			criteria.add(Restrictions.eq("id.branch", sysUser.getBranch()));
		}else {
			if(StringUtils.isNotBlank(filter.getBranch())){
				criteria.add(Restrictions.eq("id.branch", filter.getBranch()));
			}
		}
		if(StringUtils.isNotBlank(filter.getSerial())){
			criteria.add(Restrictions.eq("id.serial", filter.getSerial().trim()));
		}
		if(StringUtils.isNotBlank(filter.getName())){
			criteria.add(Restrictions.like("name", filter.getName().trim().toUpperCase(), MatchMode.ANYWHERE));
		}
		if(StringUtils.isNotBlank(filter.getTelephone())){
			criteria.add(Restrictions.eq("telephone", filter.getTelephone().trim()));
		}
		if(StringUtils.isNotBlank(filter.getStatus())){
			criteria.add(Restrictions.eq("status", filter.getStatus().trim()));
		}
		if(StringUtils.isNotBlank(filter.getType())){
			criteria.add(Restrictions.eq("type", filter.getType().trim()));
		}
		SqlUtil.buildDatesBetweenLikeCrit("date_start", filter.getDateStartFrom(), filter.getDateStartTo(), criteria);
		
		criteria.addOrder(Order.desc("dateStart"));
		criteria.addOrder(Order.desc("id.serial"));
		criteria.setMaxResults(filter.getPageSize());
		criteria.setFirstResult(filter.getOffset());
		return  criteria.list();
	}

	@SuppressWarnings("deprecation")
	public Customer findByUser(String id, SysUser sysUser) {
		Criteria criteria = this.getSession().createCriteria(this.getPersistentClass());
		criteria.add(Restrictions.eq("serial", id));
		if(StringUtils.equals(sysUser.getRole(), "USER")){
			criteria.add(Restrictions.eq("branch", sysUser.getBranch()));
		}
		return (Customer) criteria.uniqueResult();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Customer> getCustomer(String branch) {
		Criteria criteria = this.getSession().createCriteria(this.getPersistentClass());
		criteria.add(Restrictions.eq("id.branch", branch));
		
		criteria.addOrder(Order.desc("dateStart"));
		criteria.addOrder(Order.desc("id.serial"));
		return  criteria.list();
	}

}
