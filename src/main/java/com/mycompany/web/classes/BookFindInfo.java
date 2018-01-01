package com.mycompany.web.classes;

import javax.validation.constraints.Pattern;

import com.mycompany.db.entity.Author;
import com.mycompany.db.entity.PublishingHouse;
import com.mycompany.db.enumClasses.Genre;
import com.mycompany.db.enumClasses.PaperType;

/**
 * Вспомогательный класс для записи значения полей представлением bookFind и передачи объекта в контроллер 
 */
public class BookFindInfo {
	
	private String title;
	private Genre genre;
	private PublishingHouse publishingHouse;
	private Author author;
	
	private Integer yearBegin;
	private Integer yearEnd;
	
	@Pattern(regexp="\\d*", message="{Pattern.bookFindInfo.priceBegin}")
	private String priceBegin;
	
	@Pattern(regexp="\\d*", message="{Pattern.bookFindInfo.priceEnd}")
	private String priceEnd;
	
	private boolean available;
	
	private PaperType paperType;

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

	public PublishingHouse getPublishingHouse() {
		return publishingHouse;
	}

	public void setPublishingHouse(PublishingHouse publishingHouse) {
		this.publishingHouse = publishingHouse;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Integer getYearBegin() {
		return yearBegin;
	}

	public void setYearBegin(Integer yearBegin) {
		this.yearBegin = yearBegin;
	}

	public Integer getYearEnd() {
		return yearEnd;
	}

	public void setYearEnd(Integer yearEnd) {
		this.yearEnd = yearEnd;
	}

	public String getPriceBegin() {
		return priceBegin;
	}

	public void setPriceBegin(String priceBegin) {
		this.priceBegin = priceBegin;
	}

	public String getPriceEnd() {
		return priceEnd;
	}

	public void setPriceEnd(String priceEnd) {
		this.priceEnd = priceEnd;
	}

	public boolean getAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public PaperType getPaperType() {
		return paperType;
	}

	public void setPaperType(PaperType paperType) {
		this.paperType = paperType;
	}
	
}
