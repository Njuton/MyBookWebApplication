package com.mycompany.db.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.mycompany.db.embeddableClasses.Address;
import com.mycompany.db.enumClasses.Status;
import com.mycompany.db.superclasses.BaseEntity;

/**
 * Сущность "заказ"
 */
@Entity
@Table(name = "[Order]")
public class Order extends BaseEntity {
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_and_time")
	Date dateAndTime = new Date();

	@Enumerated(EnumType.STRING)
	Status status;

	@Embedded
	Address address;
	
	// двунаправленная
	@OneToMany(mappedBy = "order", orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch=FetchType.EAGER)
	List<BookOrder> bookOrders;

	// двунаправленная
	@ManyToOne
	@JoinColumn(name = "client_id", foreignKey = @ForeignKey(name = "FK_CLIENT_ORDER"))
	Client client;

	// однонаправленная
	@ManyToOne
	@JoinColumn(name = "city_id", foreignKey = @ForeignKey(name = "FK_CITY_ORDER"))
	City city;
	
	
	// override-методы
	
	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (super.equals(obj) == false)
			return false;

		Order order = (Order) obj;
		if (dateAndTime != null ? !dateAndTime.equals(order.getDateAndTime()) : order.getDateAndTime() != null)
			return false;

		if (status != null ? !status.equals(order.getStatus()) : order.getStatus() != null)
			return false;

		if (address != null ? !address.equals(order.getAddress()) : order.getAddress() != null)
			return false;

		if (client != null ? !client.equals(order.getClient()) : order.getClient() != null)
			return false;

		if (city != null ? !city.equals(order.getCity()) : order.getCity() != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = result * 31 + (dateAndTime != null ? dateAndTime.hashCode() : 0);
		result = result * 31 + (status != null ? status.hashCode() : 0);
		result = result * 31 + (address != null ? address.hashCode() : 0);
		result = result * 31 + (client != null ? client.hashCode() : 0);
		result = result * 31 + (city != null ? city.hashCode() : 0);
		return result;
	}
	
	// геттеры-сеттеры

	public List<BookOrder> getBookOrders() {
		if (bookOrders == null)
			bookOrders =  new ArrayList<BookOrder>();
		return bookOrders;
	}

	public void setBookOrders(List<BookOrder> bookOrders) {
		this.bookOrders = bookOrders;
	}
	
	public void addBookOrder(BookOrder bookOrder) {
		getBookOrders().add(bookOrder);
		bookOrder.setOrder(this);
	}
	
	public void removeBookOrder(BookOrder bookOrder) {
		getBookOrders().remove(bookOrder);
		bookOrder.setOrder(null);
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
}
