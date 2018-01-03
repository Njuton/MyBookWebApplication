create table [Order] 
(id int identity not null, 
 address nvarchar(255), 
 zip_code int, 
 date_and_time datetime2, 
 status varchar(255), 
 city_id int, 
 client_id int, 
 primary key (id))
 
create table Author 
(id int identity not null, 
 first_name nvarchar(255), 
 last_name nvarchar(255),
 primary key (id))
 
create table AuthorBook 
(author_id int not null,
 book_id int not null,
 primary key (author_id, book_id))
 
create table Book 
(id int identity not null, 
 genre varchar(255), 
 num_of_pages int, 
 num_of_selected int, 
 paper_type varchar(255), 
 price int not null, 
 quantity int not null, 
 selected bit not null, 
 title nvarchar(255), 
 year int, 
 publishing_house_id int, 
 primary key (id))
 
create table BookOrder 
(id int identity not null, 
 number int not null, 
 book_id int, 
 order_id int, 
 primary key (id))
 
create table City 
(id int identity not null,
 name nvarchar(255), 
 country_id int, 
 primary key (id))
 
create table Client 
(id int identity not null, 
 first_name nvarchar(255),
 last_name nvarchar(255), 
 address nvarchar(255), 
 zip_code int,
 email nvarchar(255),
 mobile_telephone nvarchar(255),
 city_id int,
 primary key (id))
 
create table Country 
(id int identity not null, 
 name nvarchar(255), 
 primary key (id))
 
create table PublishingHouse 
(id int identity not null, 
 name nvarchar(255), 
 primary key (id))
 
create table group_authorities 
(id bigint identity not null, 
 authority nvarchar(50), 
 group_id bigint, 
 primary key (id))

create table group_members 
(id bigint identity not null, 
 username nvarchar(50) not null, 
 group_id bigint, 
 primary key (id))

 create table groups 
(id bigint identity not null, 
 group_name nvarchar(50) not null, 
 primary key (id))

create table users 
(username nvarchar(50) not null,
 enabled bit not null, 
 password nvarchar(50), 
 primary key (username))
 
alter table [Order] 
add constraint FK_CITY_ORDER foreign key (city_id) references City

alter table [Order] 
add constraint FK_CLIENT_ORDER foreign key (client_id) references Client

alter table AuthorBook 
add constraint FK_BOOK_AUTHORBOOK foreign key (book_id) references Book

alter table AuthorBook 
add constraint FK_AUTHOR_AUTHORBOOK foreign key (author_id) references Author

alter table Book 
add constraint FK_PUBLISHINGHOUSE_BOOK foreign key (publishing_house_id) references PublishingHouse

alter table BookOrder 
add constraint FK_BOOK_BOOKORDER foreign key (book_id) references Book

alter table BookOrder 
add constraint FK_ORDER_BOOKORDER foreign key (order_id) references [Order]

alter table City
add constraint FK_COUNTRY_CITY foreign key (country_id) references Country

alter table Client 
add constraint FK_CITY_CLIENT foreign key (city_id) references City

alter table group_authorities 
add constraint GROUP_GROUP_AUTHORITIES foreign key (group_id) references groups

alter table group_members 
add constraint GROUP_GROUP_MEMBERS foreign key (group_id) references groups
