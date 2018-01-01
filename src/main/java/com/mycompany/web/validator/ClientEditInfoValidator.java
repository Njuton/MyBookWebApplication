package com.mycompany.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mycompany.dao.interfaces.ClientDao;
import com.mycompany.db.entity.Client;
import com.mycompany.web.classes.ClientEditInfo;

/**
 * Проверка добавляемого клиента на предмет существования в Базе Данных. 
 * Клиента однозначно идентифицируют параметры: имя, фамилия, мобильный телефон.
 */
public class ClientEditInfoValidator implements Validator{
	
	private ClientDao clientDao;
	
	public ClientEditInfoValidator(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return ClientEditInfo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ClientEditInfo cei = (ClientEditInfo) target;
		Client c = clientDao.getClientByParams(cei.getFirstName(), cei.getLastName(), cei.getMobileTelephone());
		if (c != null)
			errors.reject("ClientAddController.ClientExists");
	}
}
