<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css" />

<meta http-equiv="Content-Type" content="text/html;  charset=UTF-8">
<title>Create account</title>
</head>
<body>



<sf:form method="post" action="${pageContext.request.contextPath}/createaccount" commandName="user">

<table class="formtable">
<tr><td class="label">Логін: </td><td><sf:input class="control" path="username" name="username" type="text" /><br/><sf:errors path="username" cssClass="error"></sf:errors></td></tr>
<tr><td class="label">Email: </td><td><sf:input class="control"  path="email" name="email" type="text" /><br/><sf:errors path="email" cssClass="error"></sf:errors></td></tr>
<tr><td class="label">Пароль: </td><td><sf:input class="control"  path="password" name="password" type="text" /><br/><sf:errors path="password" cssClass="error"></sf:errors></td></tr>
<tr><td class="label">Повторіть пароль: </td><td><input class="control"   name="confirmpass" type="text" /><br/></td></tr>
<tr><td class="label"> </td><td><input class="control"  value="Створити акаунт" type="submit" /></td></tr>

</table>
 
</sf:form>

</body>
</html>