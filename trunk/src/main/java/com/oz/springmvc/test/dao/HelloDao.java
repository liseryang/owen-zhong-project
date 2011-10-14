package com.oz.springmvc.test.dao;

import java.util.Map;

import com.oz.springmvc.framework.orm.Page;
import com.oz.springmvc.test.domain.Hello;

public interface HelloDao extends BaseDao<Hello>{

	public Page<Hello> loadPageByCondtion(Page<Hello> page, Map conditions);
	
}
