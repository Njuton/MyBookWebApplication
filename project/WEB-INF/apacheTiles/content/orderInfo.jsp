<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/apacheTiles/main_jsp/includes.jsp" %>

<%-- Таблица с информацией о заказе --%>

<table class="orderInfoFirstTable">
	<caption>Информация о заказе:</caption>
	<tr>
		<th class="col1">Номер заказа:</th>
		<td class="col2">${order.id}</td>
	</tr>
	<tr>
		<th>Дата и время:</th>
		<td>
			<fmt:formatDate type="BOTH" value="${order.dateAndTime}" />
		</td>
	</tr>
	<tr>
		<th>Клиент:</th>
		<td>${order.client.firstName} ${order.client.lastName}</td>
	</tr>
	<tr>
		<th>Статус:</th>
		<td>${order.status}</td>
	</tr>
	<tr>
		<th>Город:</th>
		<td>${order.city.name}</td>
	</tr>
	<tr>
		<th>Адрес доставки:</th>
		<td>${order.address.address}</td>
	</tr>
	<tr>
		<th>Почтовый индекс:</th>
		<td>${order.address.zipCode}</td>
	</tr>
</table>
	
		
<%-- Таблица с заказанными книгами --%>	

<table class="orderInfoSecondTable">
	<caption>Заказанные товары:</caption>
	<tr>
		<th class="col1">Заказанные товары</th>
		<th class="col2">Количество</th>
		<th class="col3">Стоимость товаров</th>
	</tr>
	
	<c:forEach var="bookOrder"  items="${order.bookOrders}">
		<tr>
			<td>${bookOrder.book.title}</td>
			<td>${bookOrder.number}</td>
			<td>${bookOrder.number * bookOrder.book.price}</td>
		</tr>
	</c:forEach>
	
		<tr>
			<td style="color: red; font-weight: bold;">Общая стоимость заказа:</td>
			<td>
				<c:set var="total" value="0" /> 
				<c:forEach var="bookOrder" items="${order.bookOrders}">
					<c:set var="total" value="${total + bookOrder.book.price * bookOrder.number}" />
				</c:forEach>
				 <span style="font-weight: bold;">${total}</span>
			</td>
			<td></td>
		</tr>
</table>
	
<%-- Добавляем кнопки --%>

<table class="orderInfoThirdTable">
	<tr>
		<td class="col1">
			<form action="<c:url value="/orderEdit/${orderId}" />" method="get">
				<button class="editButton" type="submit">Редактировать</button>
			</form>
		</td>
		<td class="col2"></td>
		<td class="col3" style="text-align: right;">
			<form method="post">
				<button class="deleteButton" type="submit">Удалить</button>
			</form>
		</td>
	</tr>
</table>