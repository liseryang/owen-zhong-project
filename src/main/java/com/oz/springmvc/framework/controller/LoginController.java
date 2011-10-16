package com.oz.springmvc.framework.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.WebUtils;

import com.oz.springmvc.framework.domain.User;

@Controller
public class LoginController {
	private Logger log = Logger.getLogger(LoginController.class);
	@RequestMapping("/login.html")
	 protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, User user )
	    throws Exception{
		if(StringUtils.isNotBlank(user.getName())&&StringUtils.isNotBlank(user.getPw())){
			log.info("name : "+user.getName()+",password:"+user.getPw());
			WebUtils.setSessionAttribute(request, "user", user);
			return new ModelAndView(new RedirectView(getSuccessView()));
		}
		return new ModelAndView("login");
	}
	private String getSuccessView() {
		return "hello/search.html";
	}
}
