package com.mycompany.dao.interfaces;

import java.util.List;

import com.mycompany.db.entity.Author;


public interface AuthorDao extends GenericDao<Author, Integer>{
	
	/**
	 * Получить автора по его имени и фамилии
	 */
	
	Author getAuthorByFirstAndLastName(String firstName, String lastName);
	
	/**
	 * Получить автора по имени и фамилии в случае, если имя или фамилия состоят из нескольких слов
	 */
	
	Author getAuthorComplexityByFirstAndLastName(String firstName, String lastName);
	
	/**
	 * Получить авторов по имени
	 */
	
	List<Author> getAuthorByFirstName(String firstName);
	
	
	/**
	 * Получить авторов по фамилии
	 */
	
	List<Author> getAuthorByLastName(String lastName);
	
	

}
