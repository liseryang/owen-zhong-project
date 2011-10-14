<#import "/lib/spring.ftl" as spring>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
</head>
<body>
<div style="margin:0 auto;width: 250px;">
<span><@spring.message "${message}" /></span>
<a href="${url}"><@spring.message "label.return" /></a><br />
<span id="count">3</span>
</div>
</body>
<script type="text/javascript">
	var i = 3;
	var url = "${url}";
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