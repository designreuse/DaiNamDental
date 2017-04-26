package com.datawings.app.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.datawings.app.dao.ISysUserDao;
import com.datawings.app.filter.UserFilter;
import com.datawings.app.model.SysUser;

@Repository
public class SysUserDao extends BaseDao<SysUser, Integer> implements ISysUserDao{
	
	@SuppressWarnings("deprecation")
	public SysUser findByUsernameLogin(String username) {
		Criteria criteria = this.getSession().createCriteria(this.getPersistentClass());
		criteria.add(Restrictions.eq("username", username));
		criteria.add(Restrictions.eq("isEnable", true));
		return (SysUser) criteria.uniqueResult();	
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<SysUser> getUser(UserFilter filter) {
		Criteria criteria = this.getSession().createCriteria(this.getPersistentClass());
		
		if(StringUtils.isNotBlank(filter.getUsername())){
			criteria.add(Restrictions.like("username", filter.getUsername().toUpperCase().trim(), MatchMode.ANYWHERE).ignoreCase());
		}
		
		if(StringUtils.isNotBlank(filter.getRole())){
			criteria.add(Restrictions.eq("role", filter.getRole()));
		}
		
		if(StringUtils.isNotBlank(filter.getBranch())){
			criteria.add(Restrictions.eq("branch", filter.getBranch()));
		}
		criteria.addOrder(Order.asc("username"));
		
		return criteria.list();
	}

}
