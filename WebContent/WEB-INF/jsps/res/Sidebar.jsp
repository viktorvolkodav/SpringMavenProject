<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/script/jquery.js"></script>

<script type="text/javascript">
	$(document).ready(
			function() {

				var monthNames = [ "січень", "лютий", "березень", "квітень",
						"травень", "червень", "липень", "серпень", "вересень",
						"жовтень", "листопад", "грудень" ];
				var dayNames = [ "неділя", "понеділок", "вівторок", "середа",
						"четвер", "п'ятниця", "субота" ]

				var newDate = new Date();
				newDate.setDate(newDate.getDate());

				$('#Date').html(
						dayNames[newDate.getDay()] + ", " + newDate.getDate()
								+ ' ' + monthNames[newDate.getMonth()] + ' '
								+ newDate.getFullYear());

				setInterval(function() {

					var minutes = new Date().getMinutes();

					$("#min").html((minutes < 10 ? "0" : "") + minutes);
				}, 1000);

				setInterval(function() {

					var hours = new Date().getHours();

					$("#hours").html((hours < 10 ? "0" : "") + hours);
				}, 1000);
			});
</script>

</head>

<body>
	<aside class="sidebar">
	<ul>
		
		<a id="hours"></a>
		<a id="point">:</a>
		<a id="min"></a>
		<a id="point"> </a>
		<a id="Date"></a>
		<li>
			<h4>Категорії</h4>
			<ul>
				<li><a href="${pageContext.request.contextPath}/getAllBanks">Всі
						банки</a></li>
			</ul>
		</li>


		<li>
			<h4>Пошук банку</h4>
			<ul>
				<li class="text">
					<form method="GET"
						action="${pageContext.request.contextPath}/searchBank">
						<p>
							<input type="text" size="32" value="" name="searchString" />

						</p>
					</form>
				</li>
			</ul>
		</li>

		<li>
			<h4>Корисні посилання</h4>
			<ul>
				<li><a href="http://www.bank.gov.ua/control/uk/index">НБУ</a></li>
				<li><a href="http://www.fg.gov.ua/"> Фонд гарантування
						вкладів фізичних осіб</a></li>
			</ul>
		</li>

	</ul>

	</aside>

</body>
</html>