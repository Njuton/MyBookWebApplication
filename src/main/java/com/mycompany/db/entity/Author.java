package com.mycompany.db.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.mycompany.db.superclasses.Person;

/**
 * Сущность "Автор"
 */
@Entity
public class Author extends Person {
	
	// двунаправленная (владелец)
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "AuthorBook", 
			   joinColumns = @JoinColumn(name = "author_id", foreignKey = @ForeignKey(name = "FK_AUTHOR_AUTHORBOOK")), 
			   inverseJoinColumns = @JoinColumn(name = "book_id", foreignKey = @ForeignKey(name = "FK_BOOK_AUTHORBOOK")))
	Set<Book> books;

	// конструкторы

	public Author() {
		super();
	}

	public Author(String firstName, String lastName) {
		super(firstName, lastName);
	}

	// геттеры-сеттеры

	public Set<Book> getBooks() {
		if (books == null)
			books = new HashSet<Book>();
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
		for (Book b : books) {
			b.getAuthors().add(this);
		}
	}

	public void addBook(Book book) {
		getBooks().add(book);
		book.getAuthors().add(this);
	}

	public void removeBook(Book book) {
		getBooks().remove(book);
		book.getAuthors().remove(this);
	}
}
