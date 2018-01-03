package com.mycompany.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mycompany.dao.interfaces.UserDao;
import com.mycompany.web.classes.RegisterInfo;

public class RegisterInfoValidator implements Validator {
	
	private UserDao userDao;
	
	public RegisterInfoValidator(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return RegisterInfo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		RegisterInfo registerInfo = (RegisterInfo) target;

		// если введенный пароль и повтор пароля не равны => ошибка

		if (!registerInfo.getPassword().equals(registerInfo.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "RegistrationController.passwordsNotEquals");
		}
		
		// если пользователь с таким юзернэйм уже существует в БД => ошибка
		
		if (userDao.getUserByName(registerInfo.getUsername()) != null) {
			errors.reject("RegistrationController.userExists");
		}
		
	}

}
