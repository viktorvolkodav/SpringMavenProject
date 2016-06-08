<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<html>
<body>

	<header>
	<div class="width">

		<h1>
			<a href="${pageContext.request.contextPath}/"> VBank <span>
					info</span></a>
		</h1>

	</div>
	</header>


	<nav>
	<div class="width">
		<ul>

			<li class="start"><a href="${pageContext.request.contextPath}/">
					Головна </a></li>
			<li><a href="${pageContext.request.contextPath}/aboutsitepage">
					Про сайт </a></li>
			<li><a href="${pageContext.request.contextPath}/aboutsitepage">
					Про мене </a></li>

			<sec:authorize access="!isAuthenticated()">
				<li class="end"><a
					href="${pageContext.request.contextPath}/login">Вхід та
						реєстрація </a>
			</sec:authorize>

			<sec:authorize access="isAuthenticated()">

				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li><a href="${pageContext.request.contextPath}/adminpage">Адмін</a>
					<li class="end"><a href="<c:url value='/logout'/>"> Вийти</a>
						<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</sec:authorize>

				<sec:authorize access="hasRole('ROLE_USER')">
					<li><a href="${pageContext.request.contextPath}/userpage">Профіль</a>
					<li class="end"><a href="<c:url value='/logout'/>"> Вийти</a>
						<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</sec:authorize>
			</sec:authorize>
		</ul>
	</div>
	</nav>
</body>
</html>
