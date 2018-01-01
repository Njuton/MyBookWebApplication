package com.mycompany.db.entity;

import javax.persistence.Entity;

import com.mycompany.db.superclasses.NamedEntity;

/**
 * Сущность "издательство"
 */
@Entity
public class PublishingHouse extends NamedEntity{
	
	// конструкторы
	
	public PublishingHouse() {
		super();
	}
	
	public PublishingHouse(String name) {
		super(name);
	}

}
