package com.oz.springmvc.framework.tag;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.oz.springmvc.framework.domain.User;
/**
 *  SKIP_BODY          表示不用处理标签体，直接调用doEndTag()方法。
 * 	SKIP_PAGE          忽略标签后面的jsp(SUN企业级应用的首选)内容。
 * 	EVAL_PAGE          处理标签后，继续处理jsp(SUN企业级应用的首选)后面的内容。
 * 	EVAL_BODY_BUFFERED 表示需要处理标签体，且需要重新创建一个缓冲(调用setBodyContent方法)。
 * 	EVAL_BODY_INCLUDE  表示在现有的输出流对象中处理标签体，但绕过setBodyContent()和doInitBody()方法
 * 	EVAL_BODY_AGAIN    对标签体循环处理。(存在于javax.servlet.jsp.tagext.IterationTag接口中)
 * 
 *
 */
public class PermissionTag extends RequestContextAwareTag {
	private String code;
	
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	@Override
	protected int doStartTagInternal() throws Exception {
		HttpSession session = pageContext.getSession();
		User user = (User) session.getAttribute("user");
		if(user!=null&&user.getName().equals("admin")){
			return EVAL_PAGE;
		}
		return SKIP_BODY;
	}
}
