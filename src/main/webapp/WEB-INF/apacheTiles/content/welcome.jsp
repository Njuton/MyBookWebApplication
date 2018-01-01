<!-- Представление, открывающееся по context-path -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/apacheTiles/main_jsp/includes.jsp"%>

<p style="text-align: center; font-weight: bold; font-size: 15pt;">
	Вас приветствует веб-приложение учета данных о клиентах, книгах и
	заказах на них для книжного интернет-магазина.</p>

<p class="welcome_font1">Поддерживаемые данные:</p>

<ul style="list-style-type: square;">
	<li>
		<span style="font-weight: bold;">Книги:</span>
		название,автора (от одного до трёх), жанр, издательство, год издания, число
		страниц, тип бумаги, цена, число экземпляров на складе.
	</li>
	
	<li>
		<span style="font-weight: bold;">Клиенты:</span>
		имя, фамилия, мобильный телефон, email, страна, город и адрес проживания.
	</li>
	
	<li>
		<span style="font-weight: bold;">Заказы:</span> Заказы: №,
		дата и время, клиент, статус, город и адрес доставки, почтоВый индекс,
		заказанные товары, количество заказанных товаров, их стоимость, общая
		стоимость заказа</li>
</ul>

<p class="welcome_font1" >Поддерживаемые
	операции:</p>

<ul style="list-style-type: none;">
	<li>
		<span class="welcome_font2">Книги</span>
		<ul style="list-style-type: square;">
			<li>
				Поиск книг по параметрам
				<ul style="list-style-type: circle;">
					<li>
						<span style="font-style: italic;">Обычный поиск:</span> 
						название книги
					</li>
					<li>
						<span style="font-style: italic;">Расширенный поиск:</span> 
						название книги, авторы, год издательства (с/по) тип бумаги
						цена (c/по), жанр, издательство, наличие на складе
					</li>
				</ul>
			</li>
			
			<li>Добавление ноВых книг, просмотр существующих книг</li>
		</ul>
	</li>
	
	<li>
		<span class="welcome_font2">Клиенты</span>
		<ul style="list-style-type: square;">
			<li>Просмотр клиентов, их редактирование, добавление ноВых
				клиентов, удаление существующих клиентов
			</li>
		</ul>
	</li>
	<li>
		<span class="welcome_font2">Заказы</span>
		<ul style="list-style-type: square;">
			<li>Просмотр заказов, их редактирование, добавление ноВых
				заказов, редактирование существующих заказов.
			</li>
		</ul>
	</li>
	<li>
		<span class="welcome_font2">Корзина</span>
		<ul style="list-style-type: square;">
			<li>Добавление товаров в корзину, очистка корзины.
			</li>
		</ul>
	</li>
</ul>

<p class="welcome_font12">
	Об особенностях работы приложения можно узнать в разделе <a href="<c:url value="/help" />">"Помощь"</a>.
</p>

