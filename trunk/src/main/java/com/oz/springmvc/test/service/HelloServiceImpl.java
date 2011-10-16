package com.oz.springmvc.test.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oz.springmvc.framework.orm.Page;
import com.oz.springmvc.test.dao.HelloDao;
import com.oz.springmvc.test.domain.Hello;

@Service
@Transactional
public class HelloServiceImpl implements HelloService {

	private Logger log = Logger.getLogger(HelloServiceImpl.class);
	@Autowired
	private HelloDao helloDao;
	@Transactional(readOnly=true)
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
	@Transactional(readOnly=true)
	@SuppressWarnings("unchecked")
	public Page<Hello> searchHello(Page<Hello> page,Map conditions){
		return helloDao.loadPageByCondtion(page, conditions);
	}
	@Transactional(readOnly=true)
	public Hello getHello(Long id) {
		return helloDao.load(id);
	}

}
