insert into [groups] (group_name) VALUES('USER')
insert into [groups] (group_name) VALUES('ADMIN')

insert into [group_authorities] (authority, group_id) VALUES ('ROLE_USER', 1)
insert into [group_authorities] (authority, group_id) VALUES ('ROLE_ADMIN', 2)

insert into [group_members] (username, group_id) VALUES ('user', 1)
insert into [group_members] (username, group_id) VALUES ('admin', 2)

insert into [users] (username, enabled, password) VALUES ('user', 1, 'user')
insert into [users] (username, enabled, password) VALUES ('admin', 1, 'admin')

insert into [Author] (first_name, last_name) VALUES ('Дэн', 'Браун')
insert into [Author] (first_name, last_name) VALUES ('Артур', 'Конан Дойл')
insert into [Author] (first_name, last_name) VALUES ('Александр', 'Дюма')
insert into [Author] (first_name, last_name) VALUES ('Михаил', 'Шолохов')
insert into [Author] (first_name, last_name) VALUES ('Стивен', 'Хокинг')
insert into [Author] (first_name, last_name) VALUES ('Карл', 'Циммер')
insert into [Author] (first_name, last_name) VALUES ('Инт', 'Бергер')
insert into [Author] (first_name, last_name) VALUES ('Андрей', 'Горев')
insert into [Author] (first_name, last_name) VALUES ('Александр', 'Пушкин')
insert into [Author] (first_name, last_name) VALUES ('Ганс Христиан', 'Андерсен')
insert into [Author] (first_name, last_name) VALUES ('Вильям', 'Похлебкин')
insert into [Author] (first_name, last_name) VALUES ('Джейми', 'Оливер')

insert into [PublishingHouse] (name) VALUES ('Neoclassic')
insert into [PublishingHouse] (name) VALUES ('Эксмо')
insert into [PublishingHouse] (name) VALUES ('Мир книги')
insert into [PublishingHouse] (name) VALUES ('Дайджест')
insert into [PublishingHouse] (name) VALUES ('Лениздат')
insert into [PublishingHouse] (name) VALUES ('Азбука')
insert into [PublishingHouse] (name) VALUES ('АСТ')
insert into [PublishingHouse] (name) VALUES ('Розовый жираф')
insert into [PublishingHouse] (name) VALUES ('Альпина нон фрикшн')
insert into [PublishingHouse] (name) VALUES ('Книга по требованию')
insert into [PublishingHouse] (name) VALUES ('Academia')
insert into [PublishingHouse] (name) VALUES ('Росмэн')
insert into [PublishingHouse] (name) VALUES ('Добрая книга')
insert into [PublishingHouse] (name) VALUES ('Времена 2')
insert into [PublishingHouse] (name) VALUES ('КукБукс')

insert into [Book] (num_of_selected, selected, genre, num_of_pages, paper_type, price, publishing_house_id, quantity, title, year)
	VALUES (0, 0, 'DETECTIVE', 544, 'OFFSET', 1200, 1, 8, 'Код да Винчи', 2004)

insert into [Book] (num_of_selected, selected, genre, num_of_pages, paper_type, price, publishing_house_id, quantity, title, year)
	VALUES (0, 0, 'DETECTIVE', 640, 'NEWSPAPER', 120, 1, 5, 'Ангелы и демоны', 2017)
	
insert into [Book] (num_of_selected, selected, genre, num_of_pages, paper_type, price, publishing_house_id, quantity, title, year)
	VALUES (0, 0, 'DETECTIVE', 640, 'NEWSPAPER', 278, 2, 8, 'Приключения Шерлока Холмса', 2016)
	
insert into [Book] (num_of_selected, selected, genre, num_of_pages, paper_type, price, publishing_house_id, quantity, title, year)
	VALUES (0, 0, 'DETECTIVE', 448, 'NEWSPAPER', 448, 3, 10, 'Записки о Шерлоке Холмсе', 2007)
	
insert into [Book] (num_of_selected, selected, genre, num_of_pages, paper_type, price, publishing_house_id, quantity, title, year)
	VALUES (0, 0, 'HISTORY', 608, 'OFFSET', 700, 4, 5, 'Три мушкетера', 2005)
	
insert into [Book] (num_of_selected, selected, genre, num_of_pages, paper_type, price, publishing_house_id, quantity, title, year)
	VALUES (0, 0, 'HISTORY', 688, 'NEWSPAPER', 144, 5, 1, 'Поднятая целина', 1974)
	
insert into [Book] (num_of_selected, selected, genre, num_of_pages, paper_type, price, publishing_house_id, quantity, title, year)
	VALUES (0, 0, 'HISTORY', 1728, 'OFFSET', 400, 6, 2, 'Тихий дон', 2014)
	
insert into [Book] (num_of_selected, selected, genre, num_of_pages, paper_type, price, publishing_house_id, quantity, title, year)
	VALUES (0, 0, 'SCIENCE', 232, 'OFFSET', 611, 7, 5, 'Краткая история времени', 2016)
	
insert into [Book] (num_of_selected, selected, genre, num_of_pages, paper_type, price, publishing_house_id, quantity, title, year)
	VALUES (0, 0, 'SCIENCE', 360, 'NEWSPAPER', 659, 8, 2, 'Джордж и большой взрыв', 2016)
	
insert into [Book] (num_of_selected, selected, genre, num_of_pages, paper_type, price, publishing_house_id, quantity, title, year)
	VALUES (0, 0, 'SCIENCE', 368, 'OFFSET', 460, 9, 3, 'Паразит - царь природы', 2017)
	
insert into [Book] (num_of_selected, selected, genre, num_of_pages, paper_type, price, publishing_house_id, quantity, title, year)
	VALUES (0, 0, 'SCIENCE', 568, 'OFFSET', 470, 9, 3, 'Эволюция. Триумф идеи', 2017)
	
insert into [Book] (num_of_selected, selected, genre, num_of_pages, paper_type, price, publishing_house_id, quantity, title, year)
	VALUES (0, 0, 'TRANSPORT', 352, 'NEWSPAPER', 1600, 10, 1, 'Авиационные поршневые двигатели', 2012)
	
insert into [Book] (num_of_selected, selected, genre, num_of_pages, paper_type, price, publishing_house_id, quantity, title, year)
	VALUES (0, 0, 'TRANSPORT', 304, 'OFFSET', 1219, 11, 2, 'Грузовые перевозки. Учебник', 2013)
	
insert into [Book] (num_of_selected, selected, genre, num_of_pages, paper_type, price, publishing_house_id, quantity, title, year)
	VALUES (0, 0, 'TALES', 144, 'OFFSET', 183, 12, 6, 'Сказки', 2017)
	
insert into [Book] (num_of_selected, selected, genre, num_of_pages, paper_type, price, publishing_house_id, quantity, title, year)
	VALUES (0, 0, 'TALES', 256, 'OFFSET', 91, 6, 5, 'Руслан и Людмила', 2014)
	
insert into [Book] (num_of_selected, selected, genre, num_of_pages, paper_type, price, publishing_house_id, quantity, title, year)
	VALUES (0, 0, 'TALES', 144, 'OFFSET', 282, 12, 3, 'Сказки', 2017)
	
insert into [Book] (num_of_selected, selected, genre, num_of_pages, paper_type, price, publishing_house_id, quantity, title, year)
	VALUES (0, 0, 'TALES', 60, 'OFFSET', 950, 13, 2, 'Снежная королева', 2017)

insert into [Book] (num_of_selected, selected, genre, num_of_pages, paper_type, price, publishing_house_id, quantity, title, year)
	VALUES (0, 0, 'TALES', 992, 'OFFSET', 773, 14, 3, 'Большая кулинарная книга', 2016)
	
insert into [Book] (num_of_selected, selected, genre, num_of_pages, paper_type, price, publishing_house_id, quantity, title, year)
	VALUES (0, 0, 'TALES', 408, 'OFFSET', 2500, 15, 2, 'Душевная еда от Джейми Оливера', 2015)

insert into [AuthorBook] (author_id, book_id) VALUES(1,1)
insert into [AuthorBook] (author_id, book_id) VALUES(1,2)
insert into [AuthorBook] (author_id, book_id) VALUES(2,3)
insert into [AuthorBook] (author_id, book_id) VALUES(2,4)
insert into [AuthorBook] (author_id, book_id) VALUES(3,5)
insert into [AuthorBook] (author_id, book_id) VALUES(4,6)
insert into [AuthorBook] (author_id, book_id) VALUES(4,7)
insert into [AuthorBook] (author_id, book_id) VALUES(5,8)
insert into [AuthorBook] (author_id, book_id) VALUES(5,9)
insert into [AuthorBook] (author_id, book_id) VALUES(6,10)
insert into [AuthorBook] (author_id, book_id) VALUES(6,11)
insert into [AuthorBook] (author_id, book_id) VALUES(7,12)
insert into [AuthorBook] (author_id, book_id) VALUES(8,13)
insert into [AuthorBook] (author_id, book_id) VALUES(9,14)
insert into [AuthorBook] (author_id, book_id) VALUES(9,15)
insert into [AuthorBook] (author_id, book_id) VALUES(10,16)
insert into [AuthorBook] (author_id, book_id) VALUES(10,17)
insert into [AuthorBook] (author_id, book_id) VALUES(11,18)
insert into [AuthorBook] (author_id, book_id) VALUES(12,19)

insert into [Country] (name) VALUES ('Россия')
insert into [Country] (name) VALUES ('Белоруссия')

insert into [City] (name, country_id) VALUES ('Севастополь', 1)
insert into [City] (name, country_id) VALUES ('Москва', 1)
insert into [City] (name, country_id) VALUES ('Санкт-Петербург', 1)
insert into [City] (name, country_id) VALUES ('Симферополь', 1)
insert into [City] (name, country_id) VALUES ('Роств на Дону', 1)
insert into [City] (name, country_id) VALUES ('Брянск', 1)
insert into [City] (name, country_id) VALUES ('Краснодар', 1)

insert into [City] (name, country_id) VALUES ('Минск', 2)
insert into [City] (name, country_id) VALUES ('Брест', 2)
insert into [City] (name, country_id) VALUES ('Витебск', 2)
insert into [City] (name, country_id) VALUES ('Браслав', 2)

insert into [Client] (first_name, last_name, address, zip_code, email, mobile_telephone, city_id)
	VALUES ('Леонид', 'Кравчук', 'площадь Захарова, д.34, кв.1', 299002, 'l.kravchuk@mail.ru', '+79788805678', 1)

insert into [Client] (first_name, last_name, address, zip_code, email, mobile_telephone, city_id)
	VALUES ('Мария', 'Голубева', 'Ленинградское шоссе, д.112/1', 105062, 'masha_golubeva@mail.ru', '+79152458579', 2)
	
insert into [Client] (first_name, last_name, address, zip_code, email, mobile_telephone, city_id)
	VALUES ('Олег', 'Еремеев', 'улица Тимирязева, 114', 220141, 'olegka_eremeev@mail.ru', '+375172565897', 8)

insert into [Order] (address, zip_code, date_and_time, status, city_id, client_id)
	VALUES ('площадь Захарова, д.34, кв.1', 299002, '2017-12-01 10:30', 'SHIPPED', 1, 1)

insert into [BookOrder] (order_id, book_id, number) VALUES (1, 3, 1)
insert into [BookOrder] (order_id, book_id, number) VALUES (1, 2, 5)
insert into [BookOrder] (order_id, book_id, number) VALUES (1, 8, 3)
insert into [BookOrder] (order_id, book_id, number) VALUES (1, 4, 1)

insert into [Order] (address, zip_code, date_and_time, status, city_id, client_id)
	VALUES ('Общежитие № 2 ВМК МГУ', 105063, '2017-12-03 19:45', 'SHIPPED', 2, 2)

insert into [BookOrder] (order_id, book_id, number) VALUES (2, 18, 1)
insert into [BookOrder] (order_id, book_id, number) VALUES (2, 16, 3)







	
	
