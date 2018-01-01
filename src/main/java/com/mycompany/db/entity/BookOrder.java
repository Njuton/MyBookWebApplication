package com.mycompany.db.entity;

import javax.persistence.*;

import com.mycompany.db.superclasses.BaseEntity;

/**
 * Промежуточная сущность "Книга_Заказ" для организации связи ManyToMany
 */
@Entity
public class BookOrder extends BaseEntity {
	
	// двунаправленная
	@ManyToOne
	@JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "FK_ORDER_BOOKORDER"))
	Order order;
	
	// двунаправленная
	@ManyToOne
	@JoinColumn(name = "book_id", foreignKey = @ForeignKey(name = "FK_BOOK_BOOKORDER"))
	Book book;

	// количество заказанных экземпляров книг
	int number;
	
	// конструкторы
	
	public BookOrder(){
		super();
	}
	
	public BookOrder(int number, Order order, Book book) {
		this.number = number;
		this.order = order;
		this.book = book;
	}
	
	// Override-методы
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (super.equals(obj) == false)
			return false;
		
		BookOrder bookOrder = (BookOrder) obj;
		
		if (number != bookOrder.getNumber())
			return false;
		
		if (book != null ? !book.equals(bookOrder.getBook()) : bookOrder.getBook() != null)
			return false;
		
		if (order != null ? !order.equals(bookOrder.getOrder()) : bookOrder.getOrder() != null)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = result * 31 + Integer.valueOf(number).hashCode();
		result = result * 31 + (book != null ? book.hashCode() : 0);
		result = result * 31 + (order != null ? order.hashCode() : 0);
		return result;
	}
	
	// геттеры-сеттеры

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
