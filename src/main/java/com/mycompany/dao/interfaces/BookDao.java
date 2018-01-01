package com.mycompany.dao.interfaces;

import java.util.List;

import com.mycompany.db.entity.Author;
import com.mycompany.db.entity.Book;
import com.mycompany.db.entity.PublishingHouse;
import com.mycompany.db.enumClasses.Genre;
import com.mycompany.db.enumClasses.PaperType;


public interface BookDao extends GenericDao<Book, Integer>{
	
	/**
	 * Метод позволяет получить список книг по заданным параметрам
	 * 
	 * @param title             - название
	 * @param authors           - авторы
	 * @param genre             - жанр
	 * @param pablishingHous    - издательство
	 * @param paperType         - тип бумаги
	 * @param yearBegin         - год (с)
	 * @param yearEnd           - год (по)
	 * @param priceBegin        - цена (с)
	 * @param priceEnd          - цена (по)
	 * @param available         - true - товар в наличии
	 */
	
	List<Book> getBooksByParam(String title, 
							   Author authors, 
							   Genre genre, 
							   PublishingHouse publishingHouse,
							   PaperType paperType,
							   Integer yearBegin, 
							   Integer yearEnd, 
							   Integer priceBegin, 
							   Integer priceEnd, 
							   boolean available);
	
	/**
	 * Получение книг, находящихся в корзине
	 */
	List<Book> getSelectedBooks();
	
	/**
	 * Получение книг по названию
	 */
	List<Book> getBooksByTitle(String title);
	
}
