package com.mycompany.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.mycompany.dao.interfaces.CityDao;
import com.mycompany.db.entity.City;

public class StringToCityConverter implements Converter<String, City>{
	
	@Autowired
	CityDao cityDao;

	@Override
	public City convert(String str) {
		if (str == null || str.isEmpty())
			return null;
		return cityDao.getCityByName(str);
	}
}
