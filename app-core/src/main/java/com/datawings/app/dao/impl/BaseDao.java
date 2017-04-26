package com.datawings.app.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.datawings.app.dao.IBaseDao;

public abstract class BaseDao<T, ID extends Serializable> extends
		HibernateDaoSupport implements IBaseDao<T, ID> {

	protected Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public BaseDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public Class<T> getPersistentClass() {
		return this.persistentClass;
	}

	@Autowired
	public void setCurrentSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	public Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<T> findByCriteria(Criterion... criterion) {
		Criteria crit = getSession().createCriteria(this.persistentClass);
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}

	public List<T> findAll() {
		return getHibernateTemplate().loadAll(this.persistentClass);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public T find(Serializable id) {
		return (T) this.getSession().get(this.persistentClass, id);
	}

	@SuppressWarnings("deprecation")
	public Serializable save(T model) {
		return this.getSession().save(model);
	}

	@SuppressWarnings("deprecation")
	public void update(T model) {
		this.getSession().update(model);
	}

	@SuppressWarnings("deprecation")
	public void merge(T model) {
		this.getSession().merge(model);
	}

	@SuppressWarnings("deprecation")
	public void saveOrUpdate(T model) {
		this.getSession().saveOrUpdate(model);
	}

	@SuppressWarnings("deprecation")
	public void delete(T model) {
		this.getSession().delete(model);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public void deleteById(Serializable id) {
		T model = (T) this.getSession().load(persistentClass, id);
		delete(model);
	}

	@SuppressWarnings("deprecation")
	public Integer getRowsCount() {
		Criteria crit = getSession().createCriteria(persistentClass);
		return ((Long) crit.setProjection(Projections.rowCount())
				.uniqueResult()).intValue();
	}

	@SuppressWarnings("deprecation")
	public Integer getIdMax(String propertyName) {
		Criteria crit = getSession().createCriteria(persistentClass);		
		crit.setProjection(Projections.max(propertyName));
		Integer rs = (Integer) crit.uniqueResult();
		if(rs != null){
			return rs;
		}else {
			return 0;
		}	
	}
	@SuppressWarnings("deprecation")
	public void flush(){
		this.getSession().flush();
	}
}
