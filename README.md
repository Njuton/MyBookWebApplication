**Веб-приложение учета данных о клиентах, книгах и заказах на них для книжного интернет-магазина.**

**Стэк технологий:** Java 8, Spring Core, Spring MVC, JPA/Hibernate, HyperSQL, MS SQL Server, Tomcat, JSTL, JSP, Apache Tiles, Ant, Maven, HTML, CSS.

![Alt text](https://github.com/Njuton/MySiteWebApplication/blob/master/img/anim.gif "Optional title")


### Поддерживаемые данные:
------------

- **Книги:** название, автора (от одного до трёх), жанр, издательство, год издания, число страниц, тип бумаги, цена, число экземпляров на складе.
- **Клиенты**: имя, фамилия, мобильный телефон, email, страна, город и адрес проживания.
- **Заказы**: №, дата и время, клиент, статус, город и адрес доставки, почтовый индекс, заказанные товары, количество заказанных товаров, их стоимость, общая стоимость заказа.

### Поддерживаемые  операции:
------------

**Книги**
- Поиск книг по параметрам
		Обычный поиск: название книги
		Расширенный поиск: название книги, авторы, год издательства (с/по), тип бумаги, цена (c/по), жанр, издательство, наличие на складе
        
- Добавление новых книг, просмотр существующих книг

**Клиенты**
   
   Просмотр клиентов, их редактирование, добавление новых клиентов, удаление существующих клиентов.

**Заказы**
    
   Просмотр заказов, их редактирование, добавление новых заказов, редактирование существующих заказов.

**Корзина**
    
   Добавление товаров в корзину, очистка корзины.

### Требования
------------
Apache Ant 1.10.1 

Apache Maven 3.5.2

Java SE Development Kit 8

### Как развернуть и запустить приложение? (Windows)
------------

1.  **Удостоверьтесь, что**
    + у Вас установлены переменные среды CATALINA_HOME (Tomcat), M2_HOME (Maven), JAVA_HOME (JDK); 
  
    + в переменную среды Path добавлены пути к каталогу bin корневых каталогов Ant и Maven

2. **Клонируйте репозиторий**

    ```
    $ git clone https://github.com/Njuton/MySiteWebApplication.git c:\webapp
    ```
  
3. **Настройте и запустите сервер баз данных (HyperSQL или MS SQL Server)**

    По умолчанию в приложении используется сервер HyperSQL. Поэтому его необходимо просто запустить:
    ```
    $ cd c:\webapp\db\HyperSQL
    $ runServer.bat
    ```
    Если Вы используете Microsoft SQL Server: 
    + в файле src/main/resources/db.properties закомментируйте 4-ую и раскомментируйте 3-ью строчки:
        ```
        useSqlServerDB=
        #useHyperSQLDB=
        ```
    + в файле src/main/resources/jpa-config.xml закомментируйте 27-33 и раскомментируйте 17-23 строчки:
       ```
       <bean id="dataSource"
		  class="org.springframework.jdbc.datasource.DriverManagerDataSource">
		  <property name="driverClassName" value="${sqlserver.driver}" />
		  <property name="url" value="${sqlserver.url}" />
		  <property name="username" value="${sqlserver.user}" />
		  <property name="password" value="${sqlserver.password}" />
	  </bean>
      
      <!-- 	<bean id="dataSource" -->
          <!-- 		class="org.springframework.jdbc.datasource.DriverManagerDataSource"> -->
          <!-- 		<property name="driverClassName" value="${hypersql.driver}" /> -->
          <!-- 		<property name="url" value="${hypersql.url}" /> -->
          <!-- 		<property name="username" value="${hypersql.user}" /> -->
          <!-- 		<property name="password" value="${hypersql.password}" /> -->
      <!-- 	</bean> -->
       ```
       
 4. **Проинициализируйте базу данных, выполнив цель setupDB ant-скрипта build.xml:**
     ```
     $ ant setupDB 
     ```
 5. **Разверните приложение на сервере Tomcat.** Это можно сделать как с использованием Ant, так и Maven: 
     + Ant:
  
         + В файле src/main/resources/tomcat.properties свойству deploy.path присвойте значение с местонахождением папки webapps сервера Tomcat и запустите сервер
             ```
             $ cd c:\apache-tomcat-8.5.24\bin
             $ startup.but
             ```
         + Выполните цель autodeploy в ant-скрипте build.xml
              ```
             $ cd c:\webapp
             $ ant autodeploy
             ```
    + Maven:
         + В файл conf\tomcat-users.xml, находящимся в каталоге сервера Tomcat, добавьте роль и пользователя:
              ```
              <role rolename="manager-script"/>
              <user username="admin" password="admin" roles="manager-script" />
              ```
         + запустите maven плагин tomcat7 с целью deploy
             ```
             $ mvn tomcat7:deploy
             ```
 6. Перейдите на главную страницу приложения:
     ```
     http://localhost:8080/project
     ```
     
