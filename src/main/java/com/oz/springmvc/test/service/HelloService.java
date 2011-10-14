package com.oz.springmvc.test.service;

import java.util.List;
import java.util.Map;

import com.oz.springmvc.framework.orm.Page;
import com.oz.springmvc.test.domain.Hello;

public interface HelloService {
	public List<Hello> getData();

	public void saveHello(Hello hello);
	
	public void deleteHello(Long id);
	
	public Hello getHello(Long id);

	@SuppressWarnings("unchecked")
	public Page<Hello> searchHello(Page<Hello> page, Map conditions);
	
}
