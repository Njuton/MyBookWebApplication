package com.mycompany.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mycompany.dao.interfaces.AuthorDao;
import com.mycompany.db.entity.Author;

/**
 * Конверитрует значение String поля author из представления bookFind в объект Author
 */
@Component
public class StringToAuthorConverter implements Converter<String, Author>{
	
	@Autowired
	AuthorDao authorDao;

	@Override
	public Author convert(String str) {
		if (str == null || str.isEmpty() || str.equals("-"))
			return null;
		
		String[] array = str.split("\\s");

		// если фамилия или имя сложные (состоят из 2 и более слов, разделенных пробелами)
		
		if (array.length > 2) {
			return authorDao.getAuthorComplexityByFirstAndLastName(array[0], array[array.length - 1]);
		}
		
		return authorDao.getAuthorByFirstAndLastName(array[0], array[1]);
	}

}
