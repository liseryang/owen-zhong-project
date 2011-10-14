package com.oz.springmvc.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oz.springmvc.test.domain.Hello;
import com.oz.springmvc.test.service.HelloService;

@Controller
public class ViewHelloController {
	private HelloService helloService;
	@Autowired
	public void setHelloService(HelloService helloService) {
		this.helloService = helloService;
	}
	@RequestMapping("/hello/view/{id}.html")
	public String view(@PathVariable Long id,Model model){
		Hello hello = helloService.getHello(id);
		model.addAttribute(hello);
		return "hello";
	}
}
