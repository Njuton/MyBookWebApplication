package com.mycompany.db.superclasses;

import javax.persistence.MappedSuperclass;

/**
 * Суперкласс всех именованных сущностей 
 */

@MappedSuperclass
public class NamedEntity extends BaseEntity{
	
	private String name;
	
	public NamedEntity() {
		super();
	}
	
	public NamedEntity(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (super.equals(obj) == false)
			return false;
		
		NamedEntity namedEntity = (NamedEntity) obj;
		
		if (name != null ?  !name.equals(namedEntity.getName()) : namedEntity.getName() != null)
			return false;
		
		return true;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}
	
	

}
