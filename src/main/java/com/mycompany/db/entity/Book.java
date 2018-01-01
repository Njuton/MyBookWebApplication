package com.mycompany.db.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.mycompany.db.enumClasses.Genre;
import com.mycompany.db.enumClasses.PaperType;
import com.mycompany.db.superclasses.BaseEntity;

/**
 * Сущность "книга"
 */
@Entity
public class Book extends BaseEntity{
	
	// двунаправленная (цель)
	@ManyToMany(mappedBy="books", fetch=FetchType.EAGER)
	List<Author> authors;
	
	// однонаправленная
	@ManyToOne
	@JoinColumn(name="publishing_house_id", foreignKey=@ForeignKey(name="FK_PUBLISHINGHOUSE_BOOK"))
	PublishingHouse publishingHouse;
	
	// двунаправленная
	@OneToMany(mappedBy="book")
	Set<BookOrder> bookOrders;
	
	private String title;

	@Enumerated(EnumType.STRING)
	private Genre genre;

	private Integer year;

	@Column(name = "num_of_pages")
	private Integer numOfPages;

	@Enumerated(EnumType.STRING)
	@Column(name = "paper_type")
	private PaperType paperType;

	private int price;
	
	// количество товара на складе
	private int quantity;

	// true - если товар в корзине
	private boolean selected;
	
	// количество книг в корзине
	@Column(name="num_of_selected")
	private int numOfSelected;
	
	// Override-методы
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (super.equals(obj) == false) {
			return false;
		}
		
		Book book = (Book) obj;
		
		if (title != null ? !title.equals(book.getTitle()) : book.getTitle() != null) {
			return false;
		}
		
		if (genre != null ? !genre.equals(book.getGenre()) : book.getGenre() != null) {
			return false;
		}
		
		if (year != null ? !year.equals(book.getYear()) : book.getYear() != null) {
			return false;
		}
		
		if (numOfPages != null ? !numOfPages.equals(book.getNumOfPages()) : book.getNumOfPages() != null) {
			return false;
		}
		
		if (quantity != book.getQuantity() || price != book.getPrice() || selected != book.isSelected()) {
			return false;
		}
		
		if (paperType != null ? !paperType.equals(book.getPaperType()) : book.getPaperType() != null) {
			return false;
		}
		
		if (publishingHouse != null ? !publishingHouse.equals(book.getPublishingHouse()) : book.getPublishingHouse() != null) {
			return false;
		}
		
		return true;

	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = result * 31 + (title != null ? title.hashCode() : 0);
		result = result * 31 + (genre != null ? genre.hashCode() : 0);
		result = result * 31 + (year != null ? year.hashCode() : 0);
		result = result * 31 + (numOfPages != null ? numOfPages.hashCode() : 0);
		result = result * 31 + Integer.valueOf(quantity).hashCode();
		result = result * 31 + Integer.valueOf(price).hashCode();
		result = result * 31 + Boolean.valueOf(selected).hashCode();
		result = result * 31 + (paperType != null ? paperType.hashCode() : 0);
		result = result * 31 + (publishingHouse != null ? publishingHouse.hashCode() : 0);
		return result;
	}
	
	// геттеры-сеттеры

	public List<Author> getAuthors() {
		if (authors == null)
			authors = new ArrayList<Author>();
		return authors;
	}
	
	public void addAuthor(Author author) {
		getAuthors().add(author);
		author.getBooks().add(this);
	}
	
	public void removeAuthor(Author author) {
		getAuthors().remove(author);
		author.getBooks().remove(this);
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
		for (Author a : authors)
			a.getBooks().add(this);
	}

	public PublishingHouse getPublishingHouse() {
		return publishingHouse;
	}

	public void setPublishingHouse(PublishingHouse publishingHouse) {
		this.publishingHouse = publishingHouse;
	}

	public Set<BookOrder> getBookOrders() {
		if (bookOrders == null)
			bookOrders = new HashSet<BookOrder>();
		return bookOrders;
	}

	public void setBookOrders(Set<BookOrder> bookOrders) {
		this.bookOrders = bookOrders;
		for (BookOrder bo : bookOrders) {
			bo.setBook(this);
		}
	}

	public void addBookOrder(BookOrder bookOrder) {
		getBookOrders().add(bookOrder);
		bookOrder.setBook(this);
	}
	
	public void removeBookOrder(BookOrder bookOrder) {
		getBookOrders().remove(bookOrder);
		bookOrder.setBook(null);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getNumOfPages() {
		return numOfPages;
	}

	public void setNumOfPages(Integer numOfPages) {
		this.numOfPages = numOfPages;
	}

	public PaperType getPaperType() {
		return paperType;
	}

	public void setPaperType(PaperType paperType) {
		this.paperType = paperType;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public int getNumOfSelected() {
		return numOfSelected;
	}

	public void setNumOfSelected(int numOfSelected) {
		this.numOfSelected = numOfSelected;
	}

}
