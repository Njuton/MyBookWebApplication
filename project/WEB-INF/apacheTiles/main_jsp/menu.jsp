<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ include file="/WEB-INF/apacheTiles/main_jsp/includes.jsp" %>

<!-- менюшка -->

<nav class="draw-menu">
	<ul>
		<li>
			<a href="<c:url value="/" />"><i class="fa fa-home"></i>Домой</a>
		</li>
		
		<li>
			<a href="<c:url value="/bookFind" />"><i class="fa fa-book"></i>Книги</a>
			<ul>
				<li><a href="<c:url value="/bookFind" />">Расширенный поиск</a></li>
				<li><a href="<c:url value="/bookAdd" />">Добавить</a></li>
			</ul>
		</li>
		
		<li>
			<a href="<c:url value="/clientFind" />"><i class="fa fa-user"></i>Клиенты</a>
			<ul>
				<li><a href="<c:url value="/clientFind" />">Показать</a></li>
				<li><a href="<c:url value="/clientAdd" />">Добавить</a></li>
			</ul>
		</li>
			
		<li>
			<a href="<c:url value="/orderFind" />"><i class="fa fa-shopping-cart"></i>Заказы</a>
			<ul>
				<li><a href="<c:url value="/orderFind" />">Найти</a></li>
				<li><a href="<c:url value="/orderAdd" />">Оформить новый заказ</a></li>
			</ul>
		</li>
		
		<li><a href="<c:url value="/help" />"><i class="fa fa-info"></i>Помощь</a></li>
		<li><a href="<c:url value="/trash" />"><i class="fa fa-shopping-basket"></i>Корзина</a></li>
	</ul>
</nav>