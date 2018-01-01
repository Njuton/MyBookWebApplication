package com.mycompany.web.converter;

import org.springframework.core.convert.converter.Converter;

import com.mycompany.db.entity.City;

public class CityToStringConverter implements Converter<City, String>{

	@Override
	public String convert(City city) {
		if (city == null)
			return null;
		
		return city.getName();
	}
}
