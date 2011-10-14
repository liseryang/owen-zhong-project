<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp" %>
<script src="${ctx}/static/js/jquery-1.5.1.js" type="text/javascript"></script>
<script src="${ctx}/static/js/table.js" type="text/javascript"></script>
<title>Hello</title>
</head>
<body>
<div style="margin:0 auto;width: 850px;">
<form action="add.htm" method="post">
<input name="bookingNo" type="text"></input> 
<input type="submit" value="<fmt:message key="label.add"></fmt:message>"></input>
</form>
<fmt:message key="label.searchResult"></fmt:message>:
<br />

<div><a href="export.htm" target="_blank">Export</a></div>
<div><a href="javascript:void(0);" onclick="window.open('print.htm');">Print</a></div>
<form id="mainForm" action="search.htm" method="get">
		<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="page.order" id="order" value="${page.order}"/>
<table width="100%" border="1" style="border-collapse: collapse;text-align : center;">
	<thead>
		<tr>
			<th><fmt:message key="label.id"></fmt:message></th>
			<th><fmt:message key="label.no"></fmt:message></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="o" items="${page.result}">
			<tr>
				<td><c:out value="${o.id}"></c:out></td>
				<td><c:out value="${o.bookingNo}"></c:out></td>
				<td><a href="delete.htm?id=<c:out value="${o.id}"></c:out>"><fmt:message key="label.delete" /></a></td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		第${page.pageNo}页, 共${page.totalPages}页
			<a href="javascript:jumpPage(1)">首页</a>
			<s:if test="page.hasPre"><a href="javascript:jumpPage(${page.prePage})">上一页</a></s:if>
			<s:if test="page.hasNext"><a href="javascript:jumpPage(${page.nextPage})">下一页</a></s:if>
			<a href="javascript:jumpPage(${page.totalPages})">末页</a>
	</tfoot>
</table>
</form>
</div>
</body>
</html>