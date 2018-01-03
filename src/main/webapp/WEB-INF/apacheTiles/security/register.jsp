<!-- Форма регистрации нового пользователя -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/apacheTiles/main_jsp/includes.jsp"%>

<form:form modelAttribute="registerInfo" method="post">
	<table class="register">
		<caption>Форма регистрации:</caption>
		<tr>
			<th class="col1">Имя пользователя:</th>
			<td class="col2">
				<input name="username" />
			</td>
			<td class="col3 err">
				<form:errors path="username" />
			</td>
		</tr>
		
		<tr>
			<th>Пароль:</th>
			<td>
				<input type="password" name="password" />
			</td>
			<td class="err">
				<form:errors path="password" />
			</td>
		</tr>
		
		<tr>
			<th>Подтвердите пароль:</th>
			<td>
				<input type="password" name="confirmPassword" />
			</td>
			<td class="err">
				<form:errors path="confirmPassword" />
			</td>
		</tr>
		
		<%-- Ошибка: пользователь с таким username уже существует --%>
		
		<tr>	
			<td colspan="3" class="err" style="text-align: center;">
				<form:errors />
			</td>
		</tr>
		
		<tr>
			<td colspan="1">
				<button type="submit">Отправить</button>
			</td>
			<td colspan="1">
				<button type="reset">Очистить форму</button>
			</td>
		</tr>
	</table>
</form:form>