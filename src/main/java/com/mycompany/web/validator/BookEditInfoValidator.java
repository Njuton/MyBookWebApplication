package com.mycompany.web.validator;
import java.util.List;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mycompany.dao.interfaces.BookDao;
import com.mycompany.db.entity.Author;
import com.mycompany.db.entity.Book;
import com.mycompany.web.classes.BookEditInfo;

/**
 * Проверка добавляемой книги на предмет существования в Базе Данных.
 */
public class BookEditInfoValidator implements Validator{
	
	private BookDao bookDao;
	
	private int price;
	private Author author;
	
	public BookEditInfoValidator(BookDao bookDao, int price,  Author author) {
		this.price = price;
		this.author = author;
		this.bookDao = bookDao;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return BookEditInfo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		BookEditInfo bei = (BookEditInfo) target;
		
		List<Book> findBooks = bookDao.getBooksByParam(bei.getTitle(), author, bei.getGenre(), bei.getPublishingHouse(), 
				bei.getPaperType(), bei.getYear(), null, price, null, Boolean.valueOf(false));
		
		if (!findBooks.isEmpty()){
			errors.reject("BookAddController.BookExists");
		}
		
	}
}
