<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>



<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/script/jquery.js"></script>

<script type="text/javascript">
	function onLoad() {
		$("#password").keyup(checkPasswordsMatch);
		$("#confirmPassword").keyup(checkPasswordsMatch);
		$("#details").submit(cansubmit);
	}

	function cansubmit() {
		var password = $("#password").val();
		var confirmPassword = $("#confirmPassword").val();

		if (password != confirmPassword) {
			alert("Passwords do not match")
			return false;
		} else {
			return true;
		}

	}

	function checkPasswordsMatch() {
		var password = $("#password").val();
		var confirmPassword = $("#confirmPassword").val();

		if (password.length > 3 && confirmPassword.length > 3) {

			if (password == confirmPassword) {
				$("#matchpass").text(
						"Passwords match");
				$("#matchpass").addClass("valid");
				$("#matchpass").removeClass("error");

			} else {
				$("#matchpass")
						.text(
								"Passwords do not match");
				$("#matchpass").addClass("error");
				$("#matchpass").removeClass("valid");
			}
		}
	}

	$(document).ready(onLoad);
</script>



<meta http-equiv="Content-Type" content="text/html;  charset=UTF-8">
<title>Create account</title>
</head>
<body>



	<sf:form id="details" method="post"
		action="${pageContext.request.contextPath}/createaccount"
		commandName="user">

		<table class="formtable">
			<tr>
				<td class="label">Логін:</td>
				<td><sf:input class="control" path="username" name="username"
						type="text" /><br /> <sf:errors path="username" cssClass="error"></sf:errors></td>
			</tr>
			<tr>
				<td class="label">Email:</td>
				<td><sf:input class="control" path="email" name="email"
						type="text" /><br /> <sf:errors path="email" cssClass="error"></sf:errors></td>
			</tr>
			<tr>
				<td class="label">Пароль:</td>
				<td><sf:input id="password" class="control" path="password"
						name="password" type="password" /><br /> <sf:errors
						path="password" cssClass="error"></sf:errors></td>
			</tr>
			<tr>
				<td class="label">Повторіть пароль:</td>
				<td><input id="confirmPassword" class="control"
					name="confirmpass" type="password" /><br />
					<div id="matchpass"></div></td>
			</tr>
			<tr>
				<td class="label"></td>
				<td><input class="control" value="Створити акаунт"
					type="submit" /></td>
			</tr>

		</table>

	</sf:form>

</body>
</html>