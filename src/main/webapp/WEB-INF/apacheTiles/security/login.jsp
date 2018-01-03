<!-- Форма аутентификации -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/apacheTiles/main_jsp/includes.jsp"%>

<form method="post" action="<%=request.getContextPath()%>/j_spring_security_check" >
	<table class="login">
		<caption>Форма входа:</caption>
		<tr>
			<th class="col1">Имя пользователя:</th>
			<td class="col2">
				<input name="user_login" />
			</td>
		</tr>
		
		<tr>
			<th>Пароль:</th>
			<td>
				<input type="password" name="password_login" />
			</td>
		</tr>
		
		<tr>
			<th>Запомнить меня:</th>
			<td>
				<input type="checkbox" name="remember-me" />
			</td>
		</tr>
	
		<c:if test="${not empty error}">
			<tr class="err">
				<td colspan="2" style="text-align: center;">${error}</td>
			</tr>
		</c:if>
		
		<tr>
			<td>
				<input type="submit" value="Войти" />
<%-- 				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> --%>
			</td>
		</tr>
	</table>
</form>
	
