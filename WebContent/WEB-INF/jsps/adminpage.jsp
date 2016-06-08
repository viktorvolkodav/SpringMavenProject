<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/style.css" />
<title>VBank info</title>
</head>

<body>

	<h1>Server page</h1>
	<form action="updateDB" method="GET">
		<p>
			<INPUT TYPE="SUBMIT" Value="Заповнити базу">
		</p>
		<p>Здійснити заповнення бази данних банками з сайту Національного
			Банку України</p>
	</form>
	<h5>
		<font color="#0000aa">${result}</font>
	</h5>

	<form action="${pageContext.request.contextPath}/">
		<p>
			<INPUT TYPE="SUBMIT" Value="Повернутись до сайту">
		</p>
	</form>

</body>
</html>