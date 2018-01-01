<!-- Показывает информацию о клиенте -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/apacheTiles/main_jsp/includes.jsp" %>

<table class="clientInfo">
	<caption>Информация о клиенте:</caption>
	<tr>
		<th class="col1">Имя:</th>
		<td class="col2">${client.firstName}</td>
	</tr>
	<tr>
		<th>Фамилия:</th>
		<td>${client.lastName}</td>
	</tr>
	<tr>
		<th>Мобильный телефон:</th>
		<td>${client.mobileTelephone}</td>
	</tr>
	<tr>
		<th>Email:</th>
		<td>${client.email}</td>
	</tr>
	<tr>
		<th>Город проживания:</th>
		<td>${client.city.name}</td>
	</tr>
	<tr>
		<th>Адрес:</th>
		<td>${client.address.address}</td>
	</tr>
	<tr>
		<th>Почтовый индекс:</th>
		<td>${client.address.zipCode}</td>
	</tr>
	
	<tr>
		<td>
			<form action="<c:url value="/clientEdit/${client.id}" />" method="get">
				<button class="buttonEdit" type="submit">Редактировать</button>
			</form>
		</td>
		<td>
			<form method="post">
				<button class="buttonDelete" type="submit">Удалить</button>
			</form>
		</td>
	</tr>
</table>
