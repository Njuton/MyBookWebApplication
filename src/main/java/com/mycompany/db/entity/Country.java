package com.mycompany.db.entity;

import javax.persistence.Entity;

import com.mycompany.db.superclasses.NamedEntity;

/**
 * Сущность "страна"
 */
@Entity
public class Country extends NamedEntity{
	
	// конструкторы
	
	public Country() {
		super();
	}
	
	public Country(String name) {
		super(name);
	}

}
