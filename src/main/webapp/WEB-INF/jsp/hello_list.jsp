<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp" %>
<script src="${ctx}/static/js/jquery-1.5.1.js" type="text/javascript"></script>
<title>Hello</title>
</head>
<body>
<div style="margin:0 auto;width: 850px;">
<form action="add.htm" method="post"><input
	name="bookingNo" type="text"></input> <input type="submit" value="<fmt:message key="label.add"></fmt:message>"></input>
</form>
<fmt:message key="label.searchResult"></fmt:message>:
<br />

<div><a href="export.htm" target="_blank">Export</a></div>
<div><a href="javascript:void(0);" onclick="window.open('print.htm');">Print</a></div>

<table width="100%" border="1" style="border-collapse: collapse;text-align : center;">
	<thead>
		<tr>
			<th><fmt:message key="label.id"></fmt:message></th>
			<th><fmt:message key="label.no"></fmt:message></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="o" items="${list}">
			<tr>
				<td><c:out value="${o.id}"></c:out></td>
				<td><c:out value="${o.bookingNo}"></c:out></td>
				<td><a href="delete.htm?id=<c:out value="${o.id}"></c:out>"><fmt:message key="label.delete" /></a></td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<c:if test=""></c:if>
	</tfoot>
</table>
</div>
</body>
</html>