<!--  Позволяет редактировать информацию о существующем клиенте или добавлять нового клиента -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/apacheTiles/main_jsp/includes.jsp" %>

<form:form modelAttribute="clientEditInfo" method="POST">
	<table class="clientEdit">
		<tr>
			<th class="col1">Имя:</th>
			<td class="col2">
				<span style="color:red; font-weight: bold;">(*)</span>
			</td>
			<td class="col3">
				<form:input path="firstName" size="40" />
			</td>
			<%--Ошибка: имя не может быть пустым или содержать цифры --%>
			<td class="col4 err">
				<form:errors path="firstName" />
			</td>
		</tr>
		<tr>
			<th>Фамилия:</th>
			<td>
				<span style="color: red; font-weight: bold;">(*)</span>
			</td>
			<td>
				<form:input path="lastName" size="40" />
			</td>
			<%--Ошибка: фамилия не может быть пустой или содержать цифры --%>
			<td class="err">
				<form:errors path="lastName" />
			</td>
		</tr>
		<tr>
			<th>Мобильный телефон:</th>
			<td>
				<span style="color:red; font-weight: bold;">(*)</span>
			</td>
			<td>
				<form:input path="mobileTelephone" size="40" />
			</td>
			<%-- Ошибка: мобильный телефон не может быть бустым и должен иметь формат: +...--%>
			<td class="err">
				<form:errors path="mobileTelephone" />
			</td>
		</tr>
		<tr>
			<th>Email:</th>
			<td>
				<span style="color:red; font-weight: bold;">(*)</span>
			</td>
			<td>
				<form:input path="email" size="40" />
			</td>
			<%-- Ошибка: email должен иметь определенный формат --%>
			<td class="err">
				<form:errors path="email" />
			</td>
		</tr>
		<tr>
			<th>Индекс:</th>
			<td>
				<span style="color:red; font-weight: bold;">(*)</span>
			</td>
			<td>
				<form:input path="zipCode" size="40" />
			</td>
			<%-- Ошибка: индекс должен содержать только цифры и не пустой --%>
			<td class="err">
				<form:errors path="zipCode" />
			</td>
		</tr>
		<tr>
			<th>Адрес:</th>
			<td></td>
			<td>
				<form:input path="address" size="40" />
			</td>
		</tr>
		<tr>
			<th>Город:</th>
			<td>
				<span style="color:red; font-weight: bold;">(*)</span>
			</td>
			<td>
				<form:select path="city">
					<form:options items="${cities}"/>
				</form:select>
			</td>
		</tr>
		
		<%-- Если у клиента нет имени => он новый и isNew() вернет true; --%>
		<tr>
			<c:choose>
				<c:when test="${!clientEditInfo.isNew()}">
					<td><input type="submit" value="Сохранить изменения" /></td>
				</c:when>

				<c:when test="${clientEditInfo.isNew()}">
					<td><input type="submit" value="Добавить клиента" /></td>
				</c:when>
			</c:choose>
		</tr>
		<tr>
			<!-- Введенный клиент уже существует -->
			<td colspan="4" class="err" style="text-align:center">
				<form:errors />
			</td>
		</tr>
	</table>
</form:form>