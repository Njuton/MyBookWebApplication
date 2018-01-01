package com.mycompany.web.classes;

import com.mycompany.db.entity.Book;

/**
 * Вспомогательный класс {@link com.mycompany.web.classes.OrderEditInfo}
 */
public class BookOrderInfo {
	
	 /**
	  * текущее значение числа заказанных книг; 
	  * имеет тип String, поскольку проходит валидацию в {@link com.mycompany.web.validator.OrderEditInfoValidator}
	  */
	
	String currentNumber;
	
	// предыдущее значение, которые было при открытии формы
	
	int oldNumber;
	
	Book book;
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public String getCurrentNumber() {
		return currentNumber;
	}
	public void setCurrentNumber(String currentNumber) {
		this.currentNumber = currentNumber;
	}
	public int getOldNumber() {
		return oldNumber;
	}
	public void setOldNumber(int oldNumber) {
		this.oldNumber = oldNumber;
	}

}
