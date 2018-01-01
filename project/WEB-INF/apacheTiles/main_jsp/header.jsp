<!-- шапка -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/apacheTiles/main_jsp/includes.jsp" %>

<div class="head_logo">
	<img src="<c:url value="/resources/img/logo.png" />" />
</div>

<%-- 
	Поскольку из контроллера не передается menuBookInfo, 
	объект menuBookInfo типа MenuBookInfo будет создан и в него помоещено значение поля title 
--%>

<form:form modelAttribute="menuBookInfo" 
			 			method="post" 
			 			action="${pageContext.servletContext.contextPath}/menuBookFind">

	<div class="head_find_input">
		<input type="text" name="title" class="head_find_input" placeholder="Поиск книги по названию" size=35 maxlength=32 />
	</div>

	<div class="head_find_button">
		<button type="submit" class="head_find_button">Найти</button>
	</div>
	
	<div style="float: left;">
		<span class="err"><form:errors /></span>
	</div>
	
</form:form>
