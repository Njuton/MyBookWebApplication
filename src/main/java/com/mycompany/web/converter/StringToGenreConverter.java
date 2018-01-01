package com.mycompany.web.converter;

import org.springframework.core.convert.converter.Converter;

import com.mycompany.db.enumClasses.Genre;

/**
 * Конверитрует значение String поля genre из представления bookFind в объект Genre 
 */

public class StringToGenreConverter implements Converter<String, Genre>{

	@Override
	public Genre convert(String str) {
		System.out.println("String to Genre Converter");
		if (str == null || str.isEmpty() || str.equals("-"))
			return null;
		return Genre.valueOf(str);
	}

}
