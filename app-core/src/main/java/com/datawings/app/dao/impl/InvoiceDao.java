package com.datawings.app.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.datawings.app.dao.IInvoiceDao;
import com.datawings.app.filter.InvoiceFilter;
import com.datawings.app.model.Invoice;
import com.datawings.app.model.InvoiceId;
import com.datawings.app.model.SysUser;

@Repository
public class InvoiceDao extends BaseDao<Invoice, InvoiceId> implements IInvoiceDao{

	@SuppressWarnings("deprecation")
	public Integer getInvoiceRowCount(InvoiceFilter filter, SysUser sysUser) {
		Criteria criteria = this.getSession().createCriteria(this.getPersistentClass());
		if(StringUtils.equals(sysUser.getRole(), "USER")){
			criteria.add(Restrictions.eq("id.branch", sysUser.getBranch()));
		}else {
			if(StringUtils.isNotBlank(filter.getBranch())){
				criteria.add(Restrictions.eq("id.branch", filter.getBranch()));
			}
		}
		if(StringUtils.isNotBlank(filter.getSerial())){
			criteria.add(Restrictions.like("id.serial", filter.getSerial().trim(), MatchMode.ANYWHERE));
		}
		if(StringUtils.isNotBlank(filter.getNameCustomer())){
			criteria.add(Restrictions.like("nameCustomer", filter.getNameCustomer().trim().toUpperCase(), MatchMode.ANYWHERE).ignoreCase());
		}
		if(StringUtils.isNotBlank(filter.getTelephone())){
			criteria.add(Restrictions.like("telephone", filter.getTelephone().trim(), MatchMode.ANYWHERE));
		}
		return ((Number) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Invoice> getListInvoice(InvoiceFilter filter, SysUser sysUser) {
		Criteria criteria = this.getSession().createCriteria(this.getPersistentClass());
		if(StringUtils.equals(sysUser.getRole(), "USER")){
			criteria.add(Restrictions.eq("id.branch", sysUser.getBranch()));
		}else {
			if(StringUtils.isNotBlank(filter.getBranch())){
				criteria.add(Restrictions.eq("id.branch", filter.getBranch()));
			}
		}
		if(StringUtils.isNotBlank(filter.getSerial())){
			criteria.add(Restrictions.like("id.serial", filter.getSerial().trim(), MatchMode.ANYWHERE));
		}
		if(StringUtils.isNotBlank(filter.getNameCustomer())){
			criteria.add(Restrictions.like("nameCustomer", filter.getNameCustomer().trim().toUpperCase(), MatchMode.ANYWHERE).ignoreCase());
		}
		if(StringUtils.isNotBlank(filter.getTelephone())){
			criteria.add(Restrictions.like("telephone", filter.getTelephone().trim(), MatchMode.ANYWHERE));
		}
		criteria.addOrder(Order.desc("id.serial"));
		criteria.setMaxResults(filter.getPageSize());
		criteria.setFirstResult(filter.getOffset());
		return  criteria.list();
	}

}
