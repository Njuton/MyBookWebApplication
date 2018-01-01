package com.mycompany.web.validator;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mycompany.dao.interfaces.BookDao;
import com.mycompany.web.classes.BookOrderInfo;
import com.mycompany.web.classes.TrashInfo;

public class TrashValidator implements Validator{
	
	BookDao bookDao;
	
	public TrashValidator(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return TrashInfo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TrashInfo trashInfo = (TrashInfo) target;
		List<BookOrderInfo> listBookOrderInfo = trashInfo.getListBookOrderInfo();
		
		for (int i = 0; i < trashInfo.getListBookOrderInfo().size(); i++) {
			BookOrderInfo boi = listBookOrderInfo.get(i);
			
			// если не число
			if (!Pattern.compile("\\d+").matcher(boi.getCurrentNumber()).matches()) 
				errors.rejectValue("listBookOrderInfo[" + i + "].currentNumber", "TrashController.NotNumber");
			else 
				// если число == 0
				if (Integer.valueOf(boi.getCurrentNumber()) == 0) 
					errors.rejectValue("listBookOrderInfo[" + i + "].currentNumber", "TrashController.EqualsZero");
		}
	}

}
