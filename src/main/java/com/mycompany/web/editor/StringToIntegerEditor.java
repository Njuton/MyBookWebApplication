package com.mycompany.web.editor;

import java.beans.PropertyEditorSupport;

/**
 * Конверитрует значение String полей yearBegin, yearEnd из представления bookFind в объект Integer. Используется только
 * контроллером {@link com.mycompany.web.controller.BookFindController}
 */
public class StringToIntegerEditor extends PropertyEditorSupport{

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text.isEmpty() || text == null || text.equals("-")) {
			setValue(null);
			return ;
		}
		
		setValue(Integer.valueOf(text));
	}
}
