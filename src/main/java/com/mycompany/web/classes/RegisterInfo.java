package com.mycompany.web.classes;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * Вспомогательный класс для записи значений представлением register и передачи объекта в контроллер
 */
public class RegisterInfo {
	
	@Pattern(regexp="\\p{L}+", message="{Pattern.RegisterInfo.username}")
	String username;
	
	@NotEmpty(message="{NotEmpty.RegisterInfo.password}")
	String password;
	
	String confirmPassword;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
