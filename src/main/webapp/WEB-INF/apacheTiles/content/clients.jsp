<!-- Показывает информацию обо всех клиентах -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/apacheTiles/main_jsp/includes.jsp" %>

<table class="clients">
	 <caption>Найденные клиенты:</caption>
	<tr>
		<th>Имя и фамилия</th>
	</tr>
	
	<c:forEach var="client" items="${clients}" >
		<tr>
			<td>
				<a href="<c:url value="clientInfo/${client.id}"/>">${client.firstName} ${client.lastName}</a>
			</td>
		</tr>
	</c:forEach>
</table>