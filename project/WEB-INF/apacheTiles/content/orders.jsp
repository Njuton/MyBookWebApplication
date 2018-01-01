<!-- Отображает информацию обо всех заказах -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/apacheTiles/main_jsp/includes.jsp" %>

<table class="orders">
	<caption>Сделанные заказы:</caption>
	<tr>
		<th>Дата и время</th>
		<th>Клиент</th>
		<th>Статус</th>
		<th>Подробнее</th>
	</tr>
	<c:forEach var="order" items="${orders}">
		<tr>
			<td>
				<fmt:formatDate type="BOTH" value="${order.dateAndTime}" />
			</td>
			<td>
				<a href="<c:url value="/clientInfo/${order.client.id}" />">${order.client.firstName} ${order.client.lastName}</a>
			</td>
			<td>${order.status}</td>
			<td>
				<a href="<c:url value="/orderInfo/${order.id}"/>">заказ № ${order.id}</a>
			</td>
		</tr>
	</c:forEach>
</table>