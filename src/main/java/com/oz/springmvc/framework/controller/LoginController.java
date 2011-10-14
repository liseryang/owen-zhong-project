package com.oz.springmvc.framework.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.WebUtils;

import com.oz.springmvc.framework.domain.User;

public class LoginController extends SimpleFormController{
	private Logger log = Logger.getLogger(LoginController.class);
	 protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
	    throws Exception{
		User user = (User) command;
		if(StringUtils.isNotBlank(user.getName())&&StringUtils.isNotBlank(user.getPw())){
			log.info("name : "+user.getName()+",password:"+user.getPw());
			WebUtils.setSessionAttribute(request, "user", user);
			return new ModelAndView(new RedirectView(getSuccessView()));
		}
		return new ModelAndView("login");
	}
}
