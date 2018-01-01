package com.mycompany.web.classes;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.mycompany.db.entity.Author;
import com.mycompany.db.entity.PublishingHouse;
import com.mycompany.db.enumClasses.Genre;
import com.mycompany.db.enumClasses.PaperType;

/**
 * Вспомогательный класс для записи значения полей представлением bookEdit и передачи объекта в контроллер и наоборот 
 */

public class BookEditInfo {
	
	@NotEmpty(message="{NotEmpty.BookEditInfo.title}")
	private String title;
	
	// для отображения String в Enum используется конвертер по умолчанию в Spring
	
	private Genre genre;
	private Integer year;
	
	@Pattern(regexp="\\d+", message= "{Pattern.BookEditInfo.numOfPages}")
	private String numOfPages;
	
	// для отображения String в paperType будет использоваться StringToPaperTypeConverter
	
	private PaperType paperType;
	
	@NotEmpty(message="{NotEmpty.BookEditInfo.price}")
	@Pattern(regexp="\\d*", message= "{Pattern.BookEditInfo.price}")
	private String price;
	
	@NotEmpty(message="{NotEmpty.BookEditInfo.quantity}")
	@Pattern(regexp="\\d*", message= "{Pattern.BookEditInfo.quantity}")
	private String quantity;
	
	// для отображения String в publishingHouse будет использоваться StringToPublishingHouseConverter
	
	private PublishingHouse publishingHouse;
	
	// атрибут имеет тип String, а не Author, поскольку по этому полю идет валидация
	
	@Pattern(regexp="[^-]*", message= "{Pattern.BookEditInfo.author1}")
	private String author1;
	
	// для отображения String в Author будет спользоваться StringToAuthorConverter
	
	private Author author2;
	private Author author3;
	
	/**
	 * @return true, если объект только что создан
	 */
	public boolean isNew() {
		return price == null;
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
	public String getNumOfPages() {
		return numOfPages;
	}
	public void setNumOfPages(String numOfPages) {
		this.numOfPages = numOfPages;
	}
	public PaperType getPaperType() {
		return paperType;
	}
	public void setPaperType(PaperType paperType) {
		this.paperType = paperType;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public PublishingHouse getPublishingHouse() {
		return publishingHouse;
	}
	public void setPublishingHouse(PublishingHouse publishingHouse) {
		this.publishingHouse = publishingHouse;
	}
	public String getAuthor1() {
		return author1;
	}
	public void setAuthor1(String author1) {
		this.author1 = author1;
	}
	public Author getAuthor2() {
		return author2;
	}
	public void setAuthor2(Author author2) {
		this.author2 = author2;
	}
	public Author getAuthor3() {
		return author3;
	}
	public void setAuthor3(Author author3) {
		this.author3 = author3;
	}

}
