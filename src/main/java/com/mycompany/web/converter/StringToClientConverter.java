package com.mycompany.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mycompany.dao.interfaces.ClientDao;
import com.mycompany.db.entity.Client;

@Component
public class StringToClientConverter implements Converter<String, Client>{
	
	@Autowired
	ClientDao clientDao;

	/**
	 * @param str - имя, фамилия, моб.телефон, разделенные пробелом
	 */
	@Override
	public Client convert(String str) {
		
		if (str == null || str.isEmpty())
			return null;
		
		String[] arr = str.split("\\s");
		
		return clientDao.getClientByParams(arr[0], arr[1], arr[2]);
		
	}

}
