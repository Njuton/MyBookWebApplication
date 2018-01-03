package com.mycompany.dao.classes;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.dao.interfaces.AuthorDao;
import com.mycompany.db.entity.Author;

/**
 * Реализация Dao-интерфейса {@link com.mycompany.dao.interfaces.AuthorDao}
 */
@Repository
@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
public class AuthorDaoImpl extends PersistenceDao<Author, Integer> implements AuthorDao{
	
	/**
	 * получение автора по имени и фамилии
	 */
	@Override
	public Author getAuthorByFirstAndLastName(String firstName, String lastName) {
		String jpql = "SELECT a FROM Author a WHERE a.firstName=? AND a.lastName=?";
		Query query = em.createQuery(jpql);
		
		query.setParameter(0, firstName);
		query.setParameter(1, lastName);
		
		return (Author) query.getSingleResult();
	}

	/**
	 * получение авторов по имени
	 */
	@Override
	public List<Author> getAuthorByFirstName(String firstName) {
		String jpql = "SELECT a FROM Author a WHERE a.firstName=" + "'" + firstName + "'";
		return em.createQuery(jpql).getResultList();
	}
	
	/**
	 * получение авторов по фамилии
	 */
	@Override
	public List<Author> getAuthorByLastName(String lastName) {
		String jpql = "SELECT a FROM Author a WHERE a.lastName=" + "'" + lastName + "'";
		return em.createQuery(jpql).getResultList();
	}
	
	/**
	 * получение авторов по имени и фамилии в случае имен и фамилий состоящих из нескольких слов
	 *  
	 * @param firstName - первое слово в имени 
	 * @param lastName - последнее слово в фамилии
	 */
	@Override
	public Author getAuthorComplexityByFirstAndLastName(String firstName, String lastName) {
		String jpql = "SELECT a FROM Author a WHERE a.firstName LIKE CONCAT('%',?,'%') AND a.lastName LIKE CONCAT('%',?,'%')";
		Query query = em.createQuery(jpql);
		
		query.setParameter(0, firstName);
		query.setParameter(1, lastName);
		
		return (Author) query.getSingleResult();
	}
	
}
