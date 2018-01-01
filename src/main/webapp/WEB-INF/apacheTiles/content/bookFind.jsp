<!-- Поиск книг -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/apacheTiles/main_jsp/includes.jsp" %>

<form:form modelAttribute="bookFindInfo" method="POST">
	<table class="bookFind">
		<tr>
			<th>Показывать только в наличии</th>
			<td>
				<form:checkbox path="available" value="true" />
			</td>
		</tr>

		<tr>
			<th>Название книги:</th>
			<td>
				<form:input path="title" />
			</td>
		</tr>
		
		<tr>
			<th>Авторы:</th>
			<td>
				<form:select path="author">
					<form:options items="${authors}" />
				</form:select>
			</td>
		</tr>

		<tr>
			<th>Год издательства с:</th>
			<td>
				<form:select path="yearBegin">
					<form:options items="${years}" />
				</form:select>
			</td>
		</tr>

		<tr>
			<th>Год издательства по:</th>
			<td>
				<form:select path="yearEnd">
					<form:options items="${years}" />
				</form:select>
			</td>
		</tr>
		
		<tr>
			<th>Тип бумаги:</th>
			<td>
				<form:select path="paperType">
					<form:options items="${paperTypes}" />
				</form:select>
			</td>
		</tr>

		<tr>
			<th class="col1" >Цена с:</th>
			<td class="col2" >
				<form:input path="priceBegin" />
			</td>
			
			<%-- Ошибка: цена введена неверно --%>
			<td class="col3 err" >
				<form:errors path="priceBegin" />
			</td>
		</tr>

		<tr>
			<th>Цена по:</th>
			<td>
				<form:input path="priceEnd" />
			</td>
			
			<%-- Ошибка: цена введена неверно --%>
			<td class="err">
				<form:errors path="priceEnd" />
			</td>
		</tr>

		<tr>
			<th>Жанр:</th>
			<td>
				<form:select path="genre">
					<form:options items="${genres}" />
				</form:select>
			</td>
		</tr>

		<tr>
			<th>Издательство:</th>
			<td>
				<form:select path="publishingHouse">
					<form:options items="${publishingHouses}" />
				</form:select>
			</td>
		</tr>

		<tr>
			<td><input type="submit" value="Найти" /></td>
			<td><input type="reset" value="Очистить" /></td>
		</tr>
		
		<tr>
			<%-- Отображение глобальных ошибок: BookFindController.BookNotFind --%>
			<td colspan="3" style="text-align: center" class="err">
				<form:errors />
			</td>
		</tr>

	</table>
</form:form>