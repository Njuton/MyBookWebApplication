package com.mycompany.web.converter;

import org.springframework.core.convert.converter.Converter;

import com.mycompany.db.enumClasses.PaperType;

public class StringToPaperTypeConverter implements Converter<String, PaperType>{

	@Override
	public PaperType convert(String str) {
		if (str == null || str.isEmpty() || str.equals("-"))
			return null;
		
		return PaperType.valueOf(str);
	}

}
