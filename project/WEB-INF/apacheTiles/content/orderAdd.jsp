<!-- Добавление заказа -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/apacheTiles/main_jsp/includes.jsp" %>

<form:form modelAttribute="orderEditInfo" method="post">
	
	<%-- Ввод информации о заказе --%>
	
	<table class="orderAddFirstTable">
		<caption>Информация о заказе:</caption>
		<tr>	
			<th class="col1">Клиент:</th>
			<td class="col2">
				<span style="color: red; font-weight: bold">(*)</span>
			</td>
			<td class="col3">
				<form:select path="client">
					<form:options items="${clients}" />
				</form:select>
			</td>
		</tr>
			
		<tr>
			<th>Статус заказа:</th>
			<td></td>
			<td> PROCESSING </td>
		</tr>
			
		<tr>
			<th>Город:</th>
			<td>
				<span style="color: red; font-weight: bold">(*)</span>
			</td>
			<td>
				<form:select path="city">
					<form:options items="${cities}" />
				</form:select>
			</td>
		</tr>
			
		<tr>
			<th>Адрес:</th>
			<td>
				<span style="color: red; font-weight: bold">(*)</span>
			</td>
			<td>
				<form:input path="address" />
			</td>
			<%-- Ошибка: адрес не может быть пуст --%>
			<td class="col4 err">
				<form:errors class="err" path="address" />
			</td>
		</tr>
		<tr>
			<th>Индекс:</th>
			<td>
				<span style="color: red; font-weight: bold">(*)</span>
			</td>
			<td>
				<form:input path="zipCode" />
			</td>
			<%-- Ошибка: индекс должен состоять из цифр, не пуст --%>
			<td class="err">
				<form:errors path="zipCode" />
			</td>
		</tr>
	</table>
	
	<%-- 
		Ввод информации о заказанных товарах (можно менять только количество книг, остальное - 
		через корзину). 
	--%>
	
	<table class="orderAddSecondTable">
		<caption>Информация о товарах в заказе:</caption>
		<tr>
			<th class="col1">Заказанные товары</th>
			<th class="col2">Количество</th>
		</tr>
		
		<!-- Показывается в случае пустой корзины -->
		<form:errors />
		
		<!-- Обновляем значения BookOrder в существующем List<BookOrder> -->
		
		<c:forEach var="bookOrder" items="${orderEditInfo.bookOrders}" varStatus="status">
			<tr>
				<td>
					${bookOrder.book.title}
					
					<%-- заполняем объект для передачи в контроллер --%>
					
					<input type="hidden" name="bookOrders[${status.index}].book.title" value="${bookOrder.book.title}" />
					<input type="hidden" name="bookOrders[${status.index}].book.id" value="${bookOrder.book.id}" />
					<input type="hidden" name="bookOrders[${status.index}].oldNumber" value="0" />
				</td>
				<td>
					<input name="bookOrders[${status.index}].currentNumber" value="${bookOrder.oldNumber}" />
				</td>
				<%-- Введенное значение не число либо оно не натуральное --%>
				<td class="col3 err">
					<form:errors path ="bookOrders[${status.index}].currentNumber" />
				</td>
			</tr>
		</c:forEach>
		
		<%-- Кнопки --%>
		
		<tr>
			<td>
				<button type="submit">Оформить заказ</button>
			</td>
			<td></td>
			<td>
				<button type="reset">Очистить форму</button>
			</td>
		</tr>
	</table>
</form:form>