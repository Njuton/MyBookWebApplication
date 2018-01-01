package com.mycompany.dao.classes;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.mycompany.dao.interfaces.GenericDao;

/**
 *  Класс предоставляет дженерик-реализацию CRUD-операций
 */

@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
public abstract class PersistenceDao<E,K> implements GenericDao<E,K> {
	
	@PersistenceContext
	protected EntityManager em;
	
	protected Class<? extends E> daoType;
	
	public PersistenceDao() {
		daoType = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public void add(E entity) {
		em.persist(entity);
	}

	@Override
	public void update(E entity) {
		em.merge(entity);
	}

	@Override
	public void remove(E entity) {
		E mergedEntity = em.merge(entity);
		em.remove(mergedEntity);
	}

	@Override
	public E find(K key) {
		E entity = em.find(daoType, key);
		return entity;
	}

	@Override
	public List<E> list() {
		// daoType.toString() => "class com.mycompany.entity.MyClass"
		String[] arrayType = daoType.toString().split(" ");
		return em.createQuery("from " + arrayType[1]).getResultList();
	}
	
}
