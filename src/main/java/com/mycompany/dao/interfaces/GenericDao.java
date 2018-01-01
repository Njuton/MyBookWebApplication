package com.mycompany.dao.interfaces;

import java.util.List;

/**
 * Интерфейс определяет CRUD-операции
 */

public interface GenericDao<E,K>{
	void add(E entity);
	void update(E entity);
	void remove(E entity);
	E find(K key);
	List<E> list();
}
