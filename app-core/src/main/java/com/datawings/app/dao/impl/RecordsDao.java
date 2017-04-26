package com.datawings.app.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.datawings.app.common.SqlUtil;
import com.datawings.app.dao.IRecordsDao;
import com.datawings.app.filter.CustomerFiler;
import com.datawings.app.filter.RecordsFilter;
import com.datawings.app.model.Customer;
import com.datawings.app.model.Records;
import com.datawings.app.model.SysUser;

@Repository
public class RecordsDao extends BaseDao<Records, Integer> implements IRecordsDao{

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Records> getSchedule(RecordsFilter filter, SysUser sysUser) {
		Criteria criteria = this.getSession().createCriteria(this.getPersistentClass());
		
		if(StringUtils.isNotBlank(filter.getName()) || StringUtils.isNotBlank(filter.getTelephone())){
			criteria.createAlias("customer", "c", Criteria.INNER_JOIN);
		}
		if(StringUtils.equals(sysUser.getRole(), "USER")){
			criteria.add(Restrictions.eq("branch", sysUser.getBranch()));
		}else {
			if(StringUtils.isNotBlank(filter.getBranch())){
				criteria.add(Restrictions.eq("branch", filter.getBranch()));
			}
		}
		if(StringUtils.isNotBlank(filter.getSerial())){
			criteria.add(Restrictions.eq("serial", filter.getSerial()));
		}
		if(StringUtils.isNotBlank(filter.getName())){
			criteria.add(Restrictions.like("c.name", filter.getName().toUpperCase(), MatchMode.ANYWHERE));
		}
		if(StringUtils.isNotBlank(filter.getTelephone())){
			criteria.add(Restrictions.eq("c.telephone", filter.getTelephone()));
		}
		if(StringUtils.isNotBlank(filter.getDentist())){
			criteria.add(Restrictions.like("dentist", filter.getDentist()));
		}
		criteria.add(Restrictions.isNotNull("dateNext"));
		
		SqlUtil.buildDatesBetweenLikeCrit("date_next", filter.getDateNextFrom(), filter.getDateNextTo(), criteria);
		
		criteria.addOrder(Order.asc("dateNext"));
		criteria.addOrder(Order.asc("serial"));
		criteria.setMaxResults(filter.getPageSize());
		criteria.setFirstResult(filter.getOffset());
		return criteria.list();
	}

	@SuppressWarnings("deprecation")
	public Integer getScheduleRowCount(RecordsFilter filter, SysUser sysUser) {
		Criteria criteria = this.getSession().createCriteria(this.getPersistentClass());
		
		if(StringUtils.isNotBlank(filter.getName()) || StringUtils.isNotBlank(filter.getTelephone())){
			criteria.createAlias("customer", "c", Criteria.INNER_JOIN);
		}
		if(StringUtils.equals(sysUser.getRole(), "USER")){
			criteria.add(Restrictions.eq("branch", sysUser.getBranch()));
		}else {
			if(StringUtils.isNotBlank(filter.getBranch())){
				criteria.add(Restrictions.eq("branch", filter.getBranch()));
			}
		}
		if(StringUtils.isNotBlank(filter.getSerial())){
			criteria.add(Restrictions.eq("serial", filter.getSerial()));
		}
		if(StringUtils.isNotBlank(filter.getName())){
			criteria.add(Restrictions.like("c.name", filter.getName().toUpperCase(), MatchMode.ANYWHERE));
		}
		if(StringUtils.isNotBlank(filter.getTelephone())){
			criteria.add(Restrictions.eq("c.telephone", filter.getTelephone()));
		}
		if(StringUtils.isNotBlank(filter.getDentist())){
			criteria.add(Restrictions.like("dentist", filter.getDentist()));
		}
		criteria.add(Restrictions.isNotNull("dateNext"));
		SqlUtil.buildDatesBetweenLikeCrit("date_next", filter.getDateNextFrom(), filter.getDateNextTo(), criteria);
		
		return ((Number) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}

	@SuppressWarnings("deprecation")
	public Integer getCountCustomerReality(CustomerFiler filter, SysUser sysUser) {
		Criteria criteria = this.getSession().createCriteria(this.getPersistentClass());
		
		if(StringUtils.isNotBlank(filter.getName()) || StringUtils.isNotBlank(filter.getTelephone())){
			criteria.createAlias("customer", "c", Criteria.INNER_JOIN);
		}
		
		if(StringUtils.equals(sysUser.getRole(), "USER")){
			criteria.add(Restrictions.eq("branch", sysUser.getBranch()));
		}else {
			if(StringUtils.isNotBlank(filter.getBranch())){
				criteria.add(Restrictions.eq("branch", filter.getBranch()));
			}
		}
		if(StringUtils.isNotBlank(filter.getSerial())){
			criteria.add(Restrictions.eq("serial", filter.getSerial()));
		}
		if(StringUtils.isNotBlank(filter.getName())){
			criteria.add(Restrictions.like("c.name", filter.getName().toUpperCase(), MatchMode.ANYWHERE));
		}
		if(StringUtils.isNotBlank(filter.getTelephone())){
			criteria.add(Restrictions.eq("c.telephone", filter.getTelephone()));
		}
		if(StringUtils.isNotBlank(filter.getDentist())){
			criteria.add(Restrictions.like("dentist", filter.getDentist()));
		}
		
		SqlUtil.buildDatesBetweenLikeCrit("date_excute", filter.getDateStartFrom(), filter.getDateStartTo(), criteria);
		
		return ((Number) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Records> getCustomerReality(CustomerFiler filter, SysUser sysUser) {
		Criteria criteria = this.getSession().createCriteria(this.getPersistentClass());
		
		if(StringUtils.isNotBlank(filter.getName()) || StringUtils.isNotBlank(filter.getTelephone())){
			criteria.createAlias("customer", "c", Criteria.INNER_JOIN);
		}
		
		if(StringUtils.equals(sysUser.getRole(), "USER")){
			criteria.add(Restrictions.eq("branch", sysUser.getBranch()));
		}else {
			if(StringUtils.isNotBlank(filter.getBranch())){
				criteria.add(Restrictions.eq("branch", filter.getBranch()));
			}
		}
		if(StringUtils.isNotBlank(filter.getSerial())){
			criteria.add(Restrictions.eq("serial", filter.getSerial()));
		}
		if(StringUtils.isNotBlank(filter.getName())){
			criteria.add(Restrictions.like("c.name", filter.getName().toUpperCase(), MatchMode.ANYWHERE));
		}
		if(StringUtils.isNotBlank(filter.getTelephone())){
			criteria.add(Restrictions.eq("c.telephone", filter.getTelephone()));
		}
		if(StringUtils.isNotBlank(filter.getDentist())){
			criteria.add(Restrictions.like("dentist", filter.getDentist()));
		}
		
		SqlUtil.buildDatesBetweenLikeCrit("date_excute", filter.getDateStartFrom(), filter.getDateStartTo(), criteria);
		
		criteria.addOrder(Order.desc("dateExcute"));
		criteria.addOrder(Order.desc("serial"));
		criteria.setMaxResults(filter.getPageSize());
		criteria.setFirstResult(filter.getOffset());
		return criteria.list();
	}

	@SuppressWarnings("deprecation")
	public Integer getTotalCustomerReality(CustomerFiler filter, SysUser sysUser) {
		Criteria criteria = this.getSession().createCriteria(this.getPersistentClass());
		
		if(StringUtils.isNotBlank(filter.getName()) || StringUtils.isNotBlank(filter.getTelephone())){
			criteria.createAlias("customer", "c", Criteria.INNER_JOIN);
		}
		criteria.setProjection(Projections.sum("payment"));
		
		if(StringUtils.equals(sysUser.getRole(), "USER")){
			criteria.add(Restrictions.eq("branch", sysUser.getBranch()));
		}else {
			if(StringUtils.isNotBlank(filter.getBranch())){
				criteria.add(Restrictions.eq("branch", filter.getBranch()));
			}
		}
		if(StringUtils.isNotBlank(filter.getSerial())){
			criteria.add(Restrictions.eq("serial", filter.getSerial()));
		}
		if(StringUtils.isNotBlank(filter.getName())){
			criteria.add(Restrictions.like("c.name", filter.getName().toUpperCase(), MatchMode.ANYWHERE));
		}
		if(StringUtils.isNotBlank(filter.getTelephone())){
			criteria.add(Restrictions.eq("c.telephone", filter.getTelephone()));
		}
		if(StringUtils.isNotBlank(filter.getDentist())){
			criteria.add(Restrictions.like("dentist", filter.getDentist()));
		}
		
		SqlUtil.buildDatesBetweenLikeCrit("date_excute", filter.getDateStartFrom(), filter.getDateStartTo(), criteria);
		
		Long tmp = (Long) criteria.uniqueResult();
		
		Integer rs = 0;
		if(tmp != null){
			rs = tmp.intValue();
		}
		return rs;
	}

	@SuppressWarnings("deprecation")
	public Integer getCountScheduleDashboard(RecordsFilter filter, SysUser sysUser) {
		Criteria criteria = this.getSession().createCriteria(this.getPersistentClass());
		
		if(StringUtils.equals(sysUser.getRole(), "USER")){
			criteria.add(Restrictions.eq("branch", sysUser.getBranch()));
		}else {
			if(StringUtils.isNotBlank(filter.getBranch())){
				criteria.add(Restrictions.eq("branch", filter.getBranch()));
			}
		}
		criteria.add(Restrictions.isNotNull("dateNext"));
		SqlUtil.buildDatesBetweenLikeCrit("date_next", filter.getDateNextFrom(), filter.getDateNextTo(), criteria);
		
		return ((Number) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Records> getScheduleDashboard(RecordsFilter filter, SysUser sysUser, Integer pageNo) {
		Criteria criteria = this.getSession().createCriteria(this.getPersistentClass());
		
		if(StringUtils.equals(sysUser.getRole(), "USER")){
			criteria.add(Restrictions.eq("branch", sysUser.getBranch()));
		}else {
			if(StringUtils.isNotBlank(filter.getBranch())){
				criteria.add(Restrictions.eq("branch", filter.getBranch()));
			}
		}
		criteria.add(Restrictions.isNotNull("dateNext"));
		
		SqlUtil.buildDatesBetweenLikeCrit("date_next", filter.getDateNextFrom(), filter.getDateNextTo(), criteria);
		
		criteria.addOrder(Order.asc("dateNext"));
		criteria.addOrder(Order.asc("serial"));
		criteria.setMaxResults(filter.getPageSize());
		criteria.setFirstResult(filter.getOffset());
		return criteria.list();
	}

	@SuppressWarnings("deprecation")
	public void updateName(Customer customer) {
		StringBuffer sql = new StringBuffer();
		sql.append("update records set name ='" + customer.getName() + "' , telephone='" + customer.getTelephone() + "'" );
		sql.append(" , address ='" + customer.getAddress() + "'" );
		sql.append(" where serial = '" + customer.getId().getSerial() + "' and branch='" + customer.getId().getBranch() + "'");
		SQLQuery query = this.getSession().createSQLQuery(sql.toString());
		query.executeUpdate();
	}

}
