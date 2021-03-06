<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
	<table class="title">
		
		<tr>
			<td><b> Назва</b></td>
			<td><b> ${Bank.name}</b></td>
		</tr>
		<tr>
			<td>Код ЄДРПОУ:</td>
			<td>${Bank.code}</td>
		</tr>
		<tr>
			<td>МФО:</td>
			<td>${Bank.mfo}</td>
		</tr>
		<tr>
			<td>Дата реєстрації:</td>
			<td>${Bank.date}</td>
		</tr>
		<tr>
			<td>Адреса:</td>
			<td>${Bank.adress}</td>
		</tr>
		<tr>
			<td>Номер ліцензії:</td>
			<td>${Bank.license}</td>
		</tr>
		<tr>
			<td>Дата видачі ліцензії:</td>
			<td>${Bank.licensedate}</td>
		</tr>

	</table>
	<c:if test="${bankInfo.size() == null}">
		<form
			action="${pageContext.request.contextPath}/financialInfo/${Bank.code}/${Bank.mfo}">
			<p>
				<INPUT TYPE="SUBMIT" Value="Отримати фінансову інформацію">
			</p>
		</form>
	</c:if>
	<c:if test="${bankInfo.size() != null}">
		<table>
			<c:forEach var="info" items="${bankInfo}" varStatus="status">
				<tr>
					<td><b>${info.key}</b></td>

					<c:forEach var="bank" items="${info.value}">
						<tr>
							<td>${bank.key}</td>
							<td>${bank.value}</td>
						</tr>
					</c:forEach>

				</tr>
				<br>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>