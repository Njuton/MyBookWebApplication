package com.mycompany.db.superclasses;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Суперкласс всех сущностей
 */

@MappedSuperclass
public class BaseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	/**
	 * @return true, если экземпляр класса только что создан
	 */
	boolean isNew() {
		return this.id == null;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		BaseEntity baseEntity = (BaseEntity) obj;
		if (id != null ? !id.equals(baseEntity.getId()) : baseEntity.getId() != null)
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		int result = getId() != null ? getId().hashCode() : 0;
		return result;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
