<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>



<body>
	<aside class="sidebar">
	<ul>
		<li>
			<h4>Категорії</h4>
			<ul>

			</ul>
		</li>


		<li>
			<h4>Пошук банку</h4>
			<ul>
				<li class="text">
					<form method="GET" action="${pageContext.request.contextPath}/searchBank">
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