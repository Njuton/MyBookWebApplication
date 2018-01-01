<!-- Отображение информации о книге -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ include file="/WEB-INF/apacheTiles/main_jsp/includes.jsp" %>

<table class="bookInfo">
	<tr>
		<th class="col1">Название:</th>
		<td class="col2">${book.title}</td>
	</tr>
	<tr>
		<th>Имя автора:</th>
		<td><c:forEach var="author" items="${book.authors}">
			${author.firstName} ${author.lastName}; &nbsp;
			</c:forEach></td>
	</tr>
	<tr>
		<th>Жанр:</th>
		<td>${book.genre}</td>
	</tr>
	<tr>
		<th>Издательство:</th>
		<td>${book.publishingHouse.name}</td>
	</tr>
	<tr>
		<th>Год издания</th>
		<td>${book.year}</td>
	</tr>
	<tr>
		<th>Число страниц:</th>
		<td>${book.numOfPages}</td>
	</tr>
	<tr>
		<th>Тип бумаги:</th>
		<td>${book.paperType}</td>
	</tr>
	<tr>
		<th>Цена:</th>
		<td>${book.price}</td>
	</tr>
	<tr>
		<th>Число экземпляров на складе:</th>
		<td>${book.quantity}</td>
		
		<%-- Ошибка: книга уже добавлена в корзину --%>
		<td class="col3 err">${error}</td>
		
	</tr>
	<tr>
		<td>
			<%-- get-запрос к контроллеру редактирования --%>
			<form action="<c:url value="/bookEdit/${book.id}" />" method="get">
				<button type="submit">Редактировать</button>
			</form>
		</td>
		<td>
			<%-- Передаем параметр "remove=" в контроллер --%>
			<form method="post">
				<button type="submit" name="remove">Удалить</button>
			</form>
		</td>
		<td>
			<%-- Передаем параметр "addToTrash=" в контроллер --%>
			<form method="post">
				<button type="submit" name="addToTrash" value="${book.selected}">Добавить в корзину</button> 
			</form>
		</td>
	</tr>
</table>

    