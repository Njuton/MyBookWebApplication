package com.mycompany.db.embeddableClasses;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Встраиваемый класс "Адрес"
 */
@Embeddable
public class Address {
	
	private String address;
	
	@Column(name="zip_code")
	private int zipCode;
	
	public Address() {
	}
	
	public Address(String address, int zipCode) {
		this.address = address;
		this.zipCode = zipCode;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address a = (Address) obj;
		
		if (address != null ? !address.equals(a.getAddress()) : a.getAddress() != null)
			return false;
		
		if (zipCode != a.getZipCode())
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		int result = (address != null ? address.hashCode() : 0);
		result = result * 31 + zipCode;
		return result;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	
}
