<!-- Отображение информации обо всех книгах -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/apacheTiles/main_jsp/includes.jsp" %>

<table class="books">
	<tr>
		<th>Название книги</th>
		<th>Имя автора</th>
		<th>Фамилия автора</th>
		<th>Стоимость</th>
	</tr>
	
	<c:forEach var="book" items="${books}" >
		<tr>
			<td><a href="<c:url value="bookInfo/${book.id}"/>">${book.title}</a></td>
			<td>
				<c:forEach var="author" items="${book.authors}">
					${author.firstName}; 
				</c:forEach>
			</td>
			<td>
				<c:forEach var="author" items="${book.authors}">
					${author.lastName}
				</c:forEach>
			</td>
			<td>${book.price}</td>
		</tr>
	</c:forEach>
</table>