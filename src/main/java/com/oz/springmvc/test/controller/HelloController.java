package com.oz.springmvc.test.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.oz.springmvc.framework.orm.Page;
import com.oz.springmvc.test.domain.Hello;
import com.oz.springmvc.test.service.HelloService;
import com.oz.springmvc.util.ServletUtils;

@Component("hello")
public class HelloController extends MultiActionController{
	private HelloService helloService;
	@Autowired
	public void setHelloService(HelloService helloService) {
		this.helloService = helloService;
	}

	public ModelAndView list(
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) throws Exception {
		// return new ModelAndView("jsonView","list",helloService.getData());
		return new ModelAndView("hello_list", "list", helloService.getData());
	}
	
	public ModelAndView export(
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) throws Exception {
		return new ModelAndView("helloExcelView", "list", helloService.getData());
	}
	public ModelAndView print(
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) throws Exception {
		return new ModelAndView("hello-print");
	}
	
	public ModelAndView delete(
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) throws Exception {
		Long id = Long.valueOf(paramHttpServletRequest.getParameter("id"));
		helloService.deleteHello(id);
		ModelAndView modelAndView = new ModelAndView("message");
		modelAndView.addObject("message", "msg.deletesuccess");
		modelAndView.addObject("url", "search.html");	
		return modelAndView;
	}
	public ModelAndView add(
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse,Hello hello) throws Exception {
		helloService.saveHello(hello);
//		return new ModelAndView(new RedirectView("/login.htm",true));
		return new ModelAndView("redirect:search.html");//list.htm
	}
	public ModelAndView search(
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) throws Exception {
		Page<Hello> page = new Page<Hello>(5);
		if(paramHttpServletRequest.getParameter("page.pageNo")!=null){
			Integer pageNo = Integer.valueOf( paramHttpServletRequest.getParameter("page.pageNo"));
			page.setPageNo(pageNo);
		}
		Map<String, Object> conditions = ServletUtils.getParametersStartingWith(paramHttpServletRequest, "conditions.");
		page = helloService.searchHello(page, conditions);
		ModelAndView modelAndView = new ModelAndView("hello_search", "page", page);
		modelAndView.addObject("conditions", conditions);
		return modelAndView;
	}
}
