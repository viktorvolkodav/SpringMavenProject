<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<section id="content"> <br>
<br>


<h1>
	Пошук банку <BR>
</h1>
<form action="${pageContext.request.contextPath}/searchBank" method="GET">
	Запит: <INPUT TYPE="TEXT" NAME="searchString"><BR>
	<p>
		<i>(введіть повністю код ЄДРПОУ банку або назву банку або частину
			назви)</i>
	</p>

	<p>
		<input TYPE="SUBMIT" Value="Здійснити пошук"/>
	</p>
</form>
<h5>
	<font color="#FF0000">${badResult}</font>
</h5>
</section>
<body>

</body>
</html>