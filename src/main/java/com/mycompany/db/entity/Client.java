package com.mycompany.db.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.mycompany.db.embeddableClasses.Address;
import com.mycompany.db.superclasses.Person;

/**
 * Сущность "клиент"
 */
@Entity
public class Client extends Person {

	@Column(name = "mobile_telephone")
	private String mobileTelephone;

	private String email;

	@Embedded
	private Address address;
	
	// двунаправленная
	@OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST, orphanRemoval = true)
	Set<Order> orders;

	// однонаправленная
	@ManyToOne
	@JoinColumn(name = "city_id", foreignKey = @ForeignKey(name = "FK_CITY_CLIENT"))
	City city;
	
	// конструкторы

	public Client() {
		super();
	};

	public Client(String firstName, String lastName) {
		super(firstName, lastName);
	}

	// Override методы
	
	

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;

		if (super.equals(obj) == false)
			return false;

		Client client = (Client) obj;

		if (mobileTelephone != null ? !mobileTelephone.equals(client.getMobileTelephone())
				: client.getMobileTelephone() != null)
			return false;

		if (address != null ? !address.equals(client.getAddress()) : client.getAddress() != null)
			return false;

		if (city != null ? !city.equals(client.getCity()) : client.getCity() != null)
			return false;

		if (email != null ? !email.equals(client.getEmail()) : client.getEmail() != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = result * 31 + (mobileTelephone != null ? mobileTelephone.hashCode() : 0);
		result = result * 31 + (address != null ? address.hashCode() : 0);
		result = result * 31 + (city != null ? city.hashCode() : 0);
		result = result * 31 + (email != null ? email.hashCode() : 0);
		return result;
	}
	
	// геттерсы-сеттеры

	public String getMobileTelephone() {
		return mobileTelephone;
	}

	public void setMobileTelephone(String mobileTelephone) {
		this.mobileTelephone = mobileTelephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<Order> getOrders() {
		if (orders == null)
			orders = new HashSet<Order>();
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
		for (Order o : orders) {
			o.setClient(this);
		}
	}

	public void addOrder(Order order) {
		getOrders().add(order);
		order.setClient(this);
	}

	public void removeOrder(Order order) {
		getOrders().remove(order);
		order.setClient(null);
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}
