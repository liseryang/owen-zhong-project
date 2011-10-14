<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
</head>
<body>
<div style="margin:0 auto;width: 250px;">
<span><fmt:message key="${message}"></fmt:message></span>
<a href="<c:out value="${url}"></c:out>"><fmt:message key="label.return"></fmt:message></a><br />
<span id="count">3</span>
</div>
</body>
<script type="text/javascript">
	var i = 3;
	var url = "<c:out value="${url}"></c:out>";
	var redirect = function(){
		if(i>0){
			i--;
			var count = document.getElementById('count');
			count.innerHTML = i;
			setTimeout(redirect,1000);
		}else{
			window.location.href=url;
		}
	}
	setTimeout(redirect,1000);
</script>
</html>