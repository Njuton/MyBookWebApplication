package com.mycompany.db.entity;

import javax.persistence.*;

import com.mycompany.db.superclasses.NamedEntity;

/**
 * Сущность "город"
 */
@Entity
public class City extends NamedEntity{
	
	// однонаправленная
	@ManyToOne
	@JoinColumn(name="country_id", foreignKey=@ForeignKey(name="FK_COUNTRY_CITY"))
	Country country;
	
	// конструкторы 
	
	public City() {
		super();
	}
	
	public City(String name) {
		super(name);
	} 
	
	// геттеры-сеттеры

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
}
