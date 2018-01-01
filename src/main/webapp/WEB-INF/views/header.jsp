<!-- HEADER -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/includes.jsp" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<title>WebApplicationProject</title>
		
		<!-- шрифт для иконок в меню. Путь относительно context-path - "project" по каталогам webapp -->
		<link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.css" />" type="text/css">
		
		<!-- стили для меню -->
		<link rel="stylesheet" href="<c:url value="/resources/css/menu.css" />" type="text/css">
		
		<!-- стили для шапки -->
		<link rel="stylesheet" href="<c:url value="/resources/css/new-style.css" />" type="text/css">
		
		<!-- шрифт текста в пунктах меню -->
		<link href="https://fonts.googleapis.com/css?family=Cuprum" rel="stylesheet">
	</head>
	<body>
	
	<div id="container">
		
		<!-- шапка -->
		<div id="header">
			<div class="head_logo">
				<img src="<c:url value="/resources/img/logo.png" />" />
			</div>

			<div class="head_find_input">
				<input type="text" class="head_find_input" placeholder="Поиск книги по названию" size=35 maxlength=32 />
			</div>
			
			<div class="head_find_button">
				<button type="submit" class="head_find_button" >Найти</button>
			</div>
		</div>
		
		<!-- менюшка -->
		<div style="clear: both; padding: 10px;">
			<nav class="draw-menu">
				<ul>
					<li><a href="<c:url value="/" />"><i class="fa fa-home"></i>Домой</a></li>
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
					<li><a href="<c:url value="/resources/myFile.html" />"><i class="fa fa-info"></i>Помощь</a></li>
					<li><a href="<c:url value="/trash" />"><i class="fa fa-shopping-basket"></i>Корзина</a></li>
				</ul>
			</nav>
		</div>
		
		<div id="content">
