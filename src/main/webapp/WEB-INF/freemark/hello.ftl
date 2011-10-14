<#import "/lib/spring.ftl" as spring>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Welcome</title>
</head>
<body>
<div style="margin:0 auto;width: 850px;">
<form action="add.htm" method="post"><input
	name="bookingNo" type="text"></input> <input type="submit" value="<@spring.message "label.add" />"></input>
</form>
<@spring.message "label.searchResult" />:
<br />

<div><a href="export.htm" target="_blank">Export</a></div>
<div><a href="javascript:void(0);" onclick="window.open('print.htm');">Print</a></div>

<table width="100%" border="1" style="border-collapse: collapse;text-align : center;">
	<thead>
		<tr>
			<th><@spring.message "label.id" /></th>
			<th><@spring.message "label.no" /></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<#list list as o>
			<tr>
				<td>${o.id}</td>
				<td>${o.bookingNo}</td>
				<td><a href="delete.htm?id=${o.id}"><@spring.message "label.delete" /></a></td>
			</tr>
		</#list>
	</tbody>
</table>
</div>
</body>
</html>