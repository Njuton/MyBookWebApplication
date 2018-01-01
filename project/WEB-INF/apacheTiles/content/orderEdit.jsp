<!--  Редактирование заказов: часть представления - для статусов SHIPPED и CANCELLED, часть - для PROCESSING -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/apacheTiles/main_jsp/includes.jsp" %>

<%-- 
	Код отрабатывает, если статус заказа отличен от PROCESSING: SHIPPED или CANCELLED. 
	 Для таких заказов возможно редактирование только поля status 
--%>

<c:if test="${orderEditInfo.status.compareTo(statuses[0]) ne 0}">
	<form method="post">
		
		<%-- Таблица с информацией о заказе --%>
		
		<table class="orderEditNotProcessingFirst">
			<caption>Информация о заказе:</caption>
			<tr>	
				<th class="col1">Клиент:</th>
				<td class="col2">
					${orderEditInfo.client.firstName} ${orderEditInfo.client.lastName}
				</td>
			</tr>
			<tr>
				<th>Статус заказа:</th>
				<td>
				 	<select name="status">
				 	
			 			<%-- Если статус SHIPPED => делаем его selected --%>
			 			<c:if test="${orderEditInfo.status.compareTo(statuses[1]) eq 0}">
				 			<option value="${statuses[0]}">${statuses[0]}</option>
			 				<option value="${statuses[1]}" selected="selected">${statuses[1]}</option>
			 				<option value="${statuses[2]}">${statuses[2]}</option>
			 			</c:if>
			 		
			 			<%-- Если статус CANCELLED => делаем его selected --%>
			 			<c:if test="${orderEditInfo.status.compareTo(statuses[2]) eq 0}">
				 			<option value="${statuses[0]}">${statuses[0]}</option>
			 				<option value="${statuses[1]}">${statuses[1]}</option>
			 				<option value="${statuses[2]}" selected="selected">${statuses[2]}</option>
			 			</c:if>
			 		
			 		</select>
				</td>
			</tr>
			<tr>
				<th>Город:</th>
				<td>
					${orderEditInfo.city.name}
				</td>
			</tr>
			<tr>
				<th>Адрес:</th>
				<td>
					${orderEditInfo.address}
			</tr>
			<tr>
				<th>Индекс:</th>
				<td>
					${orderEditInfo.zipCode}
				</td>	
			</tr>
		</table>
		
		<%-- Таблица с заказанными товарами в заказе --%>
		
		<table class="orderEditNotProcessingSecond">	
			<caption>Заказанные товары:</caption>
			<tr>
				<th class="col1">Заказанные товары</th>
				<th class="col2">Количество</th>
			</tr>
			
			<c:forEach var="bookOrderInfo" items="${orderEditInfo.bookOrders}" varStatus="status">
				<tr>
					<td>${bookOrderInfo.book.title}</td>
					<td>${bookOrderInfo.oldNumber}</td>
				</tr>
			</c:forEach>
		</table>
		
		<%-- Блок с кнопкой --%>
		
		<div class="orderEditNotProcessingButtonSubmit">
			<button type="submit" style="width:30%;" name="sendStatus">Сохранить изменения</button>
		</div>
	</form>
</c:if>

<%-- 
	Код отрабатывает, если статус заказа отличен от SHIPPED или CANCELLED: PROCESSING. 
	 Для таких заказов возможно редактирование всех полей.
--%>

<c:if test="${orderEditInfo.status.compareTo(statuses[0]) eq 0}">
	<form:form modelAttribute="orderEditInfo" cssClass="orderEditProcessingFirst" method="post">
	
		<%-- Таблица с информацией о заказе --%>
		
		<table>
			<caption>Информация о заказе:</caption>
			
			<tr>
				<th class="col1">Клиент:</th>
				<td class="col2">
					<form:select path="client">
						<form:options items="${clients}" />
					</form:select>
				</td>
			</tr>
			
			<tr>
				<th>Статус заказа:</th>
				<td>
					<form:select path="status">
						<form:options items="${statuses}" />
					</form:select>
				</td>
			</tr>
		
			<tr>
				<th>Город:</th>
				<td>
				<form:select path="city">
					<form:options items="${cities}" />
				</form:select>
				</td>
			</tr>

			<tr>
				<th>Адрес:</th>
				<td>
					<form:input path="address" />
				</td>
				<td class="col3 err">
					<form:errors path="address" />
				</td>
			</tr>
			
			<tr>
				<th>Индекс:</th>
				<td>
					<form:input path="zipCode" />
				</td>
				<td class="err">
					<form:errors path="zipCode" />
				</td>
			</tr>
		</table>
		
		
		<%-- Таблица с товарами в заказе --%>
	
		<table class="orderEditProcessingSecond">
			<caption>Заказанные товары:</caption>
			<tr>
				<th class="col1">Наименование</th>
				<th class="col2">Количество</th>
			</tr>
		
			<%-- Обновляем значения BookOrder в существующем List<BookOrder> --%>
		
			<c:forEach var="bookOrderInfo" items="${orderEditInfo.bookOrders}" varStatus="status">
				<tr>
					<td>
						${bookOrderInfo.book.title}
					
						<%-- 
							Необходимо для инициализации новых bookOrder существующими значениями title, id, oldNumber
							для передачи в контроллер 
					 	--%>
					
						<input type="hidden" name="bookOrders[${status.index}].book.title" value="${bookOrderInfo.book.title}" />
						<input type="hidden" name="bookOrders[${status.index}].book.id" value="${bookOrderInfo.book.id}" />
						<input type="hidden" name="bookOrders[${status.index}].oldNumber" value="${bookOrderInfo.oldNumber}" />
					</td>
					<td>
						<input name="bookOrders[${status.index}].currentNumber" value="${bookOrderInfo.oldNumber}" />
					</td>
					<td class="col3 err">
						<form:errors path ="bookOrders[${status.index}].currentNumber" />
					</td>
				</tr>
			</c:forEach>
		
			<tr>
				<td>
				<button type="submit" name="save">Сохранить изменения</button>
				</td>
			</tr>
		
		</table>
	</form:form>
	
	<%-- 
		Для удаления товара из заказа переходим на урл: order_id/ номер BookOrder в list (тот же контроллер, но
		другой метод)
	--%>
	
	<table class="orderEditProcessingThird">
		<c:forEach var="bookOrderInfo" items="${orderEditInfo.bookOrders}" varStatus="status">
			<form action="${requestScope['javax.servlet.forward.request_uri']}/${status.index}" method="post">
				<tr> 
					<td>
						<button type="submit" name="remove">удалить</button>
					</td>
				</tr>
			</form>
		</c:forEach>
	</table>

	<%-- отменяем обтекание с обеих сторон float-контейнеров --%>
	
	<div style="clear: both;"></div>
</c:if>

	