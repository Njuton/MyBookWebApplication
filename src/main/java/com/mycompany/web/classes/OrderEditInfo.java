package com.mycompany.web.classes;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.mycompany.db.entity.City;
import com.mycompany.db.entity.Client;
import com.mycompany.db.enumClasses.Status;

/**
 * Вспомогательный класс для записи значения полей представлением orderEdit и передачи объекта в контроллер 
 */
public class OrderEditInfo {
	
	Status status;
	
	@NotEmpty(message="{NotEmpty.OrderEditInfo.address}")
	String address;
	
	@Pattern(regexp="\\d+", message="{Pattern.OrderEditInfo.zipCode}")
	String zipCode;
	
	City city;
	Client client;
	
	List<BookOrderInfo> bookOrders;
	
	boolean isNew() {
		return client == null;
	}
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public List<BookOrderInfo> getBookOrders() {
		if (bookOrders == null)
			bookOrders = new ArrayList<BookOrderInfo>();
		return bookOrders;
	}
	public void setBookOrders(List<BookOrderInfo> bookOrders) {
		this.bookOrders = bookOrders;
	}

}
