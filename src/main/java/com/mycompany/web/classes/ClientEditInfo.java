package com.mycompany.web.classes;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import com.mycompany.db.entity.City;

/**
 * Вспомогательный класс для записи значения полей представлением clientEdit и передачи объекта в контроллер 
 */
public class ClientEditInfo {
	
	@Pattern(regexp="\\p{L}+", message="{Pattern.ClientEditInfo.firstName}")
	String firstName;
	
	@Pattern(regexp="\\p{L}+", message="{Pattern.ClientEditInfo.lastName}")
	String lastName;
	
	@Pattern(regexp="[+][0-9]+", message="{Pattern.ClientEditInfo.mobileTelephone}") 
	String mobileTelephone;
	
	@Email(message="{Email.ClientEditInfo.email}")
	String email;
	
	@Digits(fraction=0, integer=10, message="{Digits.ClientEditInfo.zipCode}")
	String zipCode;
	
	String address;
	City city;
	
	public boolean isNew() {
		return firstName == null;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
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
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
}
