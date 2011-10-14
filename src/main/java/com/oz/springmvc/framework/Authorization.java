package com.oz.springmvc.framework;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UrlPathHelper;
import org.springframework.web.util.WebUtils;

public class Authorization extends HandlerInterceptorAdapter {
	protected final Log logger;
	private UrlPathHelper urlPathHelper;
	public Authorization(){
		logger = LogFactory.getLog(getClass());
		this.urlPathHelper = new UrlPathHelper();
	}
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    	throws Exception {
		String lookupPath = this.urlPathHelper.getLookupPathForRequest(request);
		logger.debug(lookupPath);
		Object user = WebUtils.getSessionAttribute(request, "user");
		logger.debug(user);
		if (user == null && !"/login.html".equals(lookupPath)) {
			ModelAndView modelAndView = new ModelAndView(new RedirectView("/login.html",true));
			throw new ModelAndViewDefiningException(modelAndView);
		}
		return true;
	}
}
