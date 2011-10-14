package com.oz.springmvc.framework.view;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

public class JsonViewResolver implements ViewResolver {

	public View resolveViewName(String viewName, Locale locale)
			throws Exception {
		if (viewName != null && viewName.indexOf(".json") != -1) {
			return new JsonView();
		}
		return null;
	}

}
