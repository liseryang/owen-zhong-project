package com.oz.springmvc.framework.view;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractView;

import com.oz.springmvc.framework.JacksonObjectMapper;

@SuppressWarnings("unchecked")
public class JsonView extends AbstractView implements View {

	@Override
	protected void renderMergedOutputModel(Map paramMap,
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) throws Exception {
		PrintWriter printWriter = paramHttpServletResponse.getWriter();
		String writeValueAsString = JacksonObjectMapper.getInstance().writeValueAsString(paramMap);
		printWriter.write(writeValueAsString);
	}

}
