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
import com.datawings.app.dao.IInvoiceDetailDao;
import com.datawings.app.filter.InvoiceFilter;
import com.datawings.app.model.Invoice;
import com.datawings.app.model.InvoiceDetail;
import com.datawings.app.model.SysUser;

@Repository
public class InvoiceDetailDao extends BaseDao<InvoiceDetail, Integer> implements IInvoiceDetailDao{

	@SuppressWarnings("deprecation")
	public Integer getBillRowCount(InvoiceFilter filter, SysUser sysUser) {
		Criteria criteria = this.getSession().createCriteria(this.getPersistentClass());
		
		if(StringUtils.equals(sysUser.getRole(), "USER")){
			criteria.add(Restrictions.eq("branch", sysUser.getBranch()));
		}else {
			if(StringUtils.isNotBlank(filter.getBranch())){
				criteria.add(Restrictions.eq("branch", filter.getBranch()));
			}
		}
		if(StringUtils.isNotBlank(filter.getSerial())){
			criteria.add(Restrictions.like("serial", filter.getSerial()));
		}
		if(StringUtils.isNotBlank(filter.getNameCustomer())){
			criteria.add(Restrictions.like("nameCustomer", filter.getNameCustomer().toUpperCase(), MatchMode.ANYWHERE).ignoreCase());
		}
		if(StringUtils.isNotBlank(filter.getTelephone())){
			criteria.add(Restrictions.like("telephone", filter.getTelephone()));
		}
		
		SqlUtil.buildDatesBetweenLikeCrit("date_excute", filter.getDateFrom(), filter.getDateTo(), criteria);
		
		return ((Number) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<InvoiceDetail> getBills(InvoiceFilter filter, SysUser sysUser) {
		Criteria criteria = this.getSession().createCriteria(this.getPersistentClass());
		
		if(StringUtils.equals(sysUser.getRole(), "USER")){
			criteria.add(Restrictions.eq("branch", sysUser.getBranch()));
		}else {
			if(StringUtils.isNotBlank(filter.getBranch())){
				criteria.add(Restrictions.eq("branch", filter.getBranch()));
			}
		}
		if(StringUtils.isNotBlank(filter.getSerial())){
			criteria.add(Restrictions.like("serial", filter.getSerial()));
		}
		if(StringUtils.isNotBlank(filter.getNameCustomer())){
			criteria.add(Restrictions.like("nameCustomer", filter.getNameCustomer().toUpperCase(), MatchMode.ANYWHERE).ignoreCase());
		}
		if(StringUtils.isNotBlank(filter.getTelephone())){
			criteria.add(Restrictions.like("telephone", filter.getTelephone()));
		}
		
		SqlUtil.buildDatesBetweenLikeCrit("date_excute", filter.getDateFrom(), filter.getDateTo(), criteria);
		
		criteria.addOrder(Order.desc("dateExcute"));
		criteria.addOrder(Order.desc("serial"));
		
		criteria.setMaxResults(filter.getPageSize());
		criteria.setFirstResult(filter.getOffset());
		
		return criteria.list();
	}

	@SuppressWarnings("deprecation")
	public Integer getTotalBills(InvoiceFilter filter, SysUser sysUser) {
		Criteria criteria = this.getSession().createCriteria(this.getPersistentClass());
		criteria.setProjection(Projections.sum("amount"));
		
		if(StringUtils.equals(sysUser.getRole(), "USER")){
			criteria.add(Restrictions.eq("branch", sysUser.getBranch()));
		}else {
			if(StringUtils.isNotBlank(filter.getBranch())){
				criteria.add(Restrictions.eq("branch", filter.getBranch()));
			}
		}
		if(StringUtils.isNotBlank(filter.getSerial())){
			criteria.add(Restrictions.like("serial", filter.getSerial()));
		}
		if(StringUtils.isNotBlank(filter.getNameCustomer())){
			criteria.add(Restrictions.like("nameCustomer", filter.getNameCustomer().toUpperCase(), MatchMode.ANYWHERE).ignoreCase());
		}
		if(StringUtils.isNotBlank(filter.getTelephone())){
			criteria.add(Restrictions.like("telephone", filter.getTelephone()));
		}
		SqlUtil.buildDatesBetweenLikeCrit("date_excute", filter.getDateFrom(), filter.getDateTo(), criteria);
		
		Number rs = (Number)criteria.uniqueResult();
		return rs != null ? rs.intValue() : 0;
	}

	@SuppressWarnings("deprecation")
	public void updateInf(Invoice invoice) {
		StringBuffer sql = new StringBuffer();
		sql.append("update invoice_detail set name_customer ='" + invoice.getNameCustomer() + "' , telephone='" + invoice.getTelephone() + "'" );
		sql.append(" , address ='" + invoice.getAddress() + "'");
		sql.append(" where serial = '" + invoice.getId().getSerial() + "' and branch='" + invoice.getId().getBranch() + "'");
		SQLQuery query = this.getSession().createSQLQuery(sql.toString());
		query.executeUpdate();
	}
}
