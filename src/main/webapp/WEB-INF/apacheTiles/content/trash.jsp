<!-- Работа с корзиной -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/apacheTiles/main_jsp/includes.jsp" %>

<form:form modelAttribute="trashInfo" method="post" >
	<table class="trash">
		<caption>Заказанные товары:</caption>
		<tr>
			<th class="col1">Название книги</th>
			<th class="col2">Автора</th>
			<th class="col3">Количество в корзине</th>
			<th class="col4">Стоимость 1 шт.</th>
			<th class="col5">Стоимость (с уч. экз.)</th>
		</tr>
			<c:forEach var="bookOrderInfo" items="${trashInfo.listBookOrderInfo}" varStatus="status">
				
				<%-- 
					Сохраняем информацию об id, oldNumber, title в новом объекте
					id - идентификация книги в контроллере
					oldNumber, title - в случае ошибки валидации для показа в этом представлении 
				--%>
				
				<input type="hidden" 
			 		   name="listBookOrderInfo[${status.index}].book.id" 
				       value="${bookOrderInfo.book.id}" />
			
				<input type="hidden"
				       name="listBookOrderInfo[${status.index}].oldNumber" 
				       value="${bookOrderInfo.oldNumber}" >
				   
				<input type="hidden"
				       name="listBookOrderInfo[${status.index}].book.title" 
				       value="${bookOrderInfo.book.title}" >
				   
				<tr>
					<td>${bookOrderInfo.book.title}</td>
					<td>
						<c:forEach var="author" items="${bookOrderInfo.book.authors}" varStatus="author_status" >
							${author.firstName} ${author.lastName}
							
							<%-- Передаем контроллеру информацию об авторах --%>
						
						<input type="hidden"
				   			   name="listBookOrderInfo[${status.index}].book.authors[${author_status.index}].firstName" 
				               value="${author.firstName}" >
				         
				        <input type="hidden"
				   			   name="listBookOrderInfo[${status.index}].book.authors[${author_status.index}].lastName" 
				               value="${author.lastName}" >
					</c:forEach>
				</td>
				<td>
					<input name="listBookOrderInfo[${status.index}].currentNumber" 
						   value="${bookOrderInfo.oldNumber}" />
				</td>
				   		   		
				<td>${bookOrderInfo.book.price}</td>
				<td>${bookOrderInfo.book.price * bookOrderInfo.book.numOfSelected}</td>
			</tr>
			
			<%-- Строка с ошибкой: число введено неверно --%>
			
			<tr>
				<td colspan="5" class="err" style="text-align:center;">
					<form:errors path="listBookOrderInfo[${status.index}].currentNumber" />
				</td>
			</tr>
		</c:forEach>
		
		<%-- Подсчет общей стоимости книг --%>
		
		<tr>
			<td style="color: red; font-weight: bold;" class="delBorder">Общая стоимость книг:</td>
			
			<td colspan="4" style="border-right: none; font-weight: bold;">
				<c:set var="countPrice" value="0" />
				<c:forEach var="bookOrderInfo" items="${trashInfo.listBookOrderInfo}">
					<c:set var="countPrice" 
						   value="${countPrice + bookOrderInfo.book.price * bookOrderInfo.book.numOfSelected}" />
				</c:forEach>
				${countPrice}
			</td>
		</tr>
	</table>
		
		<%-- Кнопки --%>
		
		<div class="trashSaveButton" style="width: 30%;">
			<button style="width: 100%;" type="submit" name="save">Сохранить изменения</button>
		</div>
		
		<div class="trashClearButton" style="width: 30%;">
			<button style="width: 100%;" style="submit" name="clearTrash">Очистить корзину</button>
		</div>
		
</form:form>