package com.oz.springmvc.test.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oz.springmvc.framework.orm.Page;
import com.oz.springmvc.test.dao.HelloDao;
import com.oz.springmvc.test.domain.Hello;

@Service
public class HelloServiceImpl implements HelloService {

	private Logger log = Logger.getLogger(HelloServiceImpl.class);
	
	private HelloDao helloDao;
	
	public List<Hello> getData() {
		List<Hello> loadHelloList = helloDao.loadAll();
		return loadHelloList;
	}
	public void saveHello(Hello hello){
		hello = helloDao.save(hello);
		log.info("id -> : "+ hello.getId());
	}
	
	public void deleteHello(Long id) {
		helloDao.delete(id);
	}
	@SuppressWarnings("unchecked")
	public Page<Hello> searchHello(Page<Hello> page,Map conditions){
		return helloDao.loadPageByCondtion(page, conditions);
	}
	@Autowired
	public void setHelloDao(HelloDao helloDao) {
		this.helloDao = helloDao;
	}

}
