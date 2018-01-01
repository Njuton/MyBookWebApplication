<!--  Позволяет редактировать информацию об уже существующей книге или добавлять новую книгу -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/apacheTiles/main_jsp/includes.jsp" %>

<%-- Если значение атрибута action не указано => post-запрос будет отправлен на тот url,
	откуда пришел ответ, содержащий статику данного представления: bookAddController/bookEditController --%>

<form:form modelAttribute="bookEditInfo" method="POST">
	<table class="bookEdit">
		<tr>
			<th class="col1">Название книги:</th>
			<td class="col2">
				<span style="color:red; font-weight: bold;">(*)</span>
			</td>
			<td class="col3">
				<form:input path="title" size="40" />
			</td>
			
			<%-- Ошибка: название не введено--%>
			<td class="col4 err">
				<form:errors path="title" />
			</td>
		</tr>

		<tr>
			<th>Автор 1:</th>
			<td>
				<span style="color:red; font-weight: bold;">(*)</span>
			</td>
			<td><form:select path="author1">
					<form:options items="${authors}" />
				</form:select>
			</td>
			<td class="err">
				<form:errors path="author1" />
			</td>
		</tr>

		<tr>
			<th>Автор 2:</th>
			<td></td>
			<td>
				<form:select path="author2">
					<form:options items="${authors}" />
				</form:select>
			</td>
		</tr>

		<tr>
			<th>Автор 3:</th>
			<td></td>
			<td><form:select path="author3">
					<form:options items="${authors}" />
				</form:select>
			</td>
		</tr>

		<tr>
			<th>Жанр:</th>
			<td>
				<span style="color:red; font-weight: bold;">(*)</span>
			</td>
			<td>
				<form:select path="genre">
					<form:options items="${genres}" />
				</form:select>
			</td>
			<td class="err"><form:errors path="genre" /></td>
		</tr>

		<tr>
			<th>Издательство:</th>
			<td>
				<span style="color:red; font-weight: bold;">(*)</span>
			</td>
			<td>
				<form:select path="publishingHouse">
					<form:options items="${publishingHouses}" />
				</form:select>
			</td>
		</tr>

		<tr>
			<th>Тип бумаги:</th>
			<td>
				<span style="color:red; font-weight: bold;">(*)</span>
			</td>
			<td>
				<form:select path="paperType">
					<form:options items="${paperTypes}" />
				</form:select>
			</td>
		</tr>

		<tr>
			<th>Число страниц:</th>
			<td>
				<span style="color:red; font-weight: bold;">(*)</span>
			</td>
			<td>
				<form:input path="numOfPages" /> 
			</td>
			
			<%-- Ошибка: число страниц введено неверно--%>
			<td class="err">
				<form:errors path="numOfPages" />
			</td>
		</tr>
		
		<tr>
			<th>Год издания:</th>
			<td>
				<span style="color:red; font-weight: bold;">(*)</span>
			</td>
			<td>
				<form:select path="year">
					<form:options items="${years}" />
				</form:select>
			</td>
		</tr>

		<tr>
			<th>Цена: </th>
			<td>
				<span style="color:red; font-weight: bold;">(*)</span>
			</td>
			<td>
				<form:input path="price" />
			</td>
			
			<%-- Ошибка: цена введена неверно --%>
			<td class="err">
				<form:errors path="price" />
			</td>
		</tr>

		<tr>
			<th>Количество товара на складе:</th>
			<td>
				<span style="color:red; font-weight: bold;">(*)</span>
			</td>
			<td>
				<form:input path="quantity" />
			</td>
			
			<%-- Ошибка: количество товара введено неверно --%>
			<td class="err">
				<form:errors path="quantity" />
			</td>
		</tr>
		
		<%-- Если ранее цена не указана (книга новая) => представление добавлении новой книги, иначе 
			представление редактирования информации о старой --%>
		<tr>
			<c:choose>
				<c:when test="${!bookEditInfo.isNew()}">
					<td style="text-align: center">
						<input type="submit" value="Сохранить изменения" />
					</td>
				</c:when>
				
				<c:when test="${bookEditInfo.isNew()}">
					<td style="text-align: center">
						<input type="submit" value="Добавить книгу" />
					</td>
				</c:when>
			</c:choose>
			
			<td></td>
			
			<td style="text-align: center">
				<input type="reset" value="Сбросить" />
			</td>
		</tr>
		<tr>
			<!--  Ошибка: книга уже существует -->
			<td colspan="4" style="text-align:center" class="err"><form:errors /></td>
		</tr>

	</table>
</form:form>

