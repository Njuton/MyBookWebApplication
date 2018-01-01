package com.mycompany.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.mycompany.dao.interfaces.PublishingHouseDao;
import com.mycompany.db.entity.PublishingHouse;

/**
 *  Конверитрует значение String поля publishingHouse из представления bookFind в объект PublishingHouse 
 */
public class StringToPublishingHouseConverter implements Converter<String, PublishingHouse> {
	
	@Autowired
	PublishingHouseDao publishingHouseDao;

	@Override
	public PublishingHouse convert(String str) {
		System.out.println("String to PublishingHouse Converter");
		if (str == null || str.isEmpty() || str.equals("-"))
			return null;
		
		return publishingHouseDao.getPublishingHouseByName(str);
	}

}
