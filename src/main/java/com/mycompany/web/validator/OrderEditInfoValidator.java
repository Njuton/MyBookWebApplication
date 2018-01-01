package com.mycompany.web.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mycompany.dao.interfaces.BookDao;
import com.mycompany.db.entity.Book;
import com.mycompany.web.classes.BookOrderInfo;
import com.mycompany.web.classes.OrderEditInfo;

/**
 * Проверяет, что при вводе количества книг в форму, значение "количества" было != 0, представляло число
 * и что такое количество есть на складе.
 */
public class OrderEditInfoValidator implements Validator {
	
	BookDao bookDao;

	public OrderEditInfoValidator(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return OrderEditInfo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		OrderEditInfo orderEditInfo = (OrderEditInfo) target;
		for (int i = 0; i < orderEditInfo.getBookOrders().size(); i++ ) {
			
			BookOrderInfo boi = orderEditInfo.getBookOrders().get(i);
			
			// если не число
			
			if (!Pattern.compile("\\d+").matcher(boi.getCurrentNumber()).matches()) {
				errors.rejectValue("bookOrders[" + i + "].currentNumber", "OrderEditController.NotNumber");
				return;
			}
			
			// если число == 0 
			
			if (Long.valueOf(boi.getCurrentNumber()) == 0)
				errors.rejectValue("bookOrders[" + i + "].currentNumber", "OrderEditController.EqualsZero");
			
			Book book = bookDao.find(boi.getBook().getId());
			
			// если введенное число превышает количество книг на складе
			
			if ((book.getQuantity() + boi.getOldNumber() - Long.valueOf(boi.getCurrentNumber())) < 0) {
				errors.rejectValue("bookOrders[" + i + "].currentNumber", "OrderEditController.BookNotAvailable");
			}
		}

	}

}
