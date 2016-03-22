<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/static/style.css" />


<title>VBank info</title>
</head>


<body>
	<div id="container">
		<jsp:include page="/WEB-INF/jsps/res/Header.jsp"></jsp:include>

		<div id="body" class="width">
			<section id="content"> <c:if test="${Bank != null}">
				<jsp:include page="/WEB-INF/jsps/res/PrintOneBankInfo.jsp"></jsp:include>
			</c:if> 
			<c:if test="${BankList.size() != null}">
				<table>
					<c:forEach var="Bank" items="${BankList}" varStatus="status">
						<tr>
							<td>${status.count}</td>
							<td><a
								href="${pageContext.request.contextPath}/searchBank?searchString=${Bank.code}">
									${Bank.name}</a></td>
						</tr>
					</c:forEach>
				</table>
		</c:if> 
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
