package com.mycompany.db.superclasses;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Суперкласс всех людей
 */

@MappedSuperclass
public class Person extends BaseEntity{
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	public Person() {
		super();
	}
	
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (super.equals(obj) == false)
			return false;
		
		Person person = (Person) obj;
		if (firstName != null ? !firstName.equals(person.getFirstName()) : person.getFirstName() != null)
			return false;
		if (lastName != null ? !lastName.equals(person.getLastName()) : person.getLastName() != null)
			return false;
		
		return true;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		return result;
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

}
