package com.oz.springmvc.test.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.oz.springmvc.framework.orm.Page;
import com.oz.springmvc.test.domain.Hello;
import com.oz.springmvc.test.service.HelloService;
import com.oz.springmvc.util.ServletUtils;


@Controller
@RequestMapping("/hello")
public class HelloController extends MultiActionController{
	private HelloService helloService;
	@Autowired
	public void setHelloService(HelloService helloService) {
		this.helloService = helloService;
	}

	@RequestMapping("list.html")
	public ModelAndView list() throws Exception {
		return new ModelAndView("hello_list", "list", helloService.getData());
	}
	@RequestMapping("export.html")
	public ModelAndView export() throws Exception {
		return new ModelAndView("helloExcelView", "list", helloService.getData());
	}
	@RequestMapping("print.html")
	public ModelAndView print() throws Exception {
		return new ModelAndView("hello-print");
	}
	@RequestMapping("delete.html")
	public ModelAndView delete(@RequestParam Long id) throws Exception {
		helloService.deleteHello(id);
		ModelAndView modelAndView = new ModelAndView("message");
		modelAndView.addObject("message", "msg.deletesuccess");
		modelAndView.addObject("url", "search.html");	
		return modelAndView;
	}
	
	@RequestMapping("add.html")
	public String add(Hello hello){
		helloService.saveHello(hello);
		return "redirect:search.html";
	}
	@RequestMapping("search.html")
	public ModelAndView search(
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) throws Exception {
		Page<Hello> page = new Page<Hello>(Page.DEFAULT_PAGE_NO);
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
