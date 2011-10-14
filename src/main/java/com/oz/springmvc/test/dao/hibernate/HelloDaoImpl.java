package com.oz.springmvc.test.dao.hibernate;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.oz.springmvc.framework.orm.Page;
import com.oz.springmvc.test.dao.HelloDao;
import com.oz.springmvc.test.dao.HibernateDAO;
import com.oz.springmvc.test.domain.Hello;


public class HelloDaoImpl extends HibernateDAO<Hello> implements HelloDao{

	public Page<Hello> loadPageByCondtion(Page<Hello> page, Map conditions) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
