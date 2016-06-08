<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/style.css" />


<title>VBank info</title>
</head>


<body>
	<div id="container">
		<jsp:include page="/WEB-INF/jsps/res/Header.jsp"></jsp:include>

		<div id="body" class="width">
		<section id="content">
		<table class="title">
	<tr>
		<td><b> Назва акаунту</b></td>
		<td><b> ${User.username}</b></td>
	</tr>
	<tr>
		<td>email</td>
		<td>${User.email}</td>
	</tr>
	</table>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			</section>

			<jsp:include page="/WEB-INF/jsps/res/Sidebar.jsp"></jsp:include>
			<div class="clear"></div>

		</div>
		<jsp:include page="/WEB-INF/jsps/res/Footer.jsp"></jsp:include>

	</div>
</body>

</html>


	
