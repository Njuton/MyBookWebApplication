<!-- Основной шаблон приложения -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ include file="/WEB-INF/apacheTiles/main_jsp/includes.jsp" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>"MyBook" WebApplication</title>

		<!-- шрифт для иконок в меню -->
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css" />

		<!-- стили для меню Путь относительно context-path - "project" (src/main/webapp) -->
		<link rel="stylesheet" href="<c:url value="/resources/css/menu.css" />" type="text/css">

		<!-- стили для всего проекта -->
		<link rel="stylesheet" href="<c:url value="/resources/css/custom-style.css" />" type="text/css">

		<!-- шрифт текста в пунктах меню -->
		<link href="https://fonts.googleapis.com/css?family=Cuprum" rel="stylesheet">
	</head>
	<body>
		<div id="container">
			 
			 <div id="header">
			 	<tiles:insertAttribute name="header" />
			 </div>
			 
			 <div style="clear: both; padding: 10px;">
			 	<tiles:insertAttribute name="menu" />
			 </div>
			 
			 <div id="content">
			 	<tiles:insertAttribute name="content" />
			 </div> 
			 
			 <div>
			 	<tiles:insertAttribute name="footer" />
			 </div>
			 
		</div>
	</body>
	
	