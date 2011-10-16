<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>File Upload</title>
</head>
<body>
<h1>Please upload a file</h1>
<form method="post" action="file_upload.html" enctype="multipart/form-data">
<input type="file" name="file"/>
<input type="submit"/>
</form>
</body>
</html>