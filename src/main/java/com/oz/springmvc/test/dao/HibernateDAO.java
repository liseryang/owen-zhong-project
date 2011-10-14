package com.oz.springmvc.test.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

@SuppressWarnings("unchecked")
public class HibernateDAO<T> extends HibernateDaoSupport implements BaseDao<T> {
	Class<T> entityClass;
	
	public HibernateDAO(){
		Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]; 
		this.entityClass = entityClass;
	}
	@Autowired
    public void anyMethodName(SessionFactory sessionFactory)
    {
        setSessionFactory(sessionFactory);
    }
	public T save(T t){
		getHibernateTemplate().save(t);
		return t;
	}
	public void update(T t){
		getHibernateTemplate().update(t);
	}
	
	public void delete (T t){
		getHibernateTemplate().delete(t);
	}
	public void delete (Long id){
		T load = this.load(id);
		this.delete(load);
	}
	public T load(Long id){
		return (T) getHibernateTemplate().load(entityClass, id);
	}
	public List<T> loadAll(){
		return getHibernateTemplate().loadAll(entityClass);
	}
}
