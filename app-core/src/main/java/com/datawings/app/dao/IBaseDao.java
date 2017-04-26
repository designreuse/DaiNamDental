package com.datawings.app.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;

public interface IBaseDao<T, ID extends Serializable> {

	Class<T> getPersistentClass();

	void setCurrentSessionFactory(SessionFactory sessionFactory);

	Session getCurrentSession();

	List<T> findByCriteria(Criterion... criterion);

	List<T> findAll();

	T find(Serializable id);

	Serializable save(T model);

	void update(T model);

	void merge(T model);

	void saveOrUpdate(T model);

	void delete(T model);

	void deleteById(Serializable id);
	
	Integer getRowsCount();

	Integer getIdMax(String propertyName);
	
	void flush();
}