<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
	<table class="title">
		<thread> </thread>
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
</body>
</html>