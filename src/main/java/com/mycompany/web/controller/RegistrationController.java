package com.mycompany.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mycompany.dao.interfaces.GroupDao;
import com.mycompany.dao.interfaces.GroupMemberDao;
import com.mycompany.dao.interfaces.UserDao;
import com.mycompany.db.entity.security.Group;
import com.mycompany.db.entity.security.GroupMember;
import com.mycompany.db.entity.security.User;
import com.mycompany.web.classes.RegisterInfo;
import com.mycompany.web.validator.RegisterInfoValidator;

@Controller
public class RegistrationController {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	GroupDao groupDao;
	
	@Autowired
	GroupMemberDao groupMemberDao;
	
	@GetMapping("/register")
	void registerGet(RegisterInfo registerInfo) {
	}
	
	@PostMapping("/register")
	String registerPost(@Valid @ModelAttribute("registerInfo") RegisterInfo registerInfo, BindingResult errors) {
		
		new RegisterInfoValidator(userDao).validate(registerInfo, errors);
		
		if (errors.hasErrors()) {
			return "register";
		}
		
		// сохраняем нового пользователя в БД и присваеваем ему группу "пользователи"
		
		User user = new User();
		user.setEnabled(true);
		user.setPassword(registerInfo.getPassword());
		user.setUsername(registerInfo.getUsername());
		
		userDao.add(user);
		
		GroupMember groupMember = new GroupMember();
		Group group = groupDao.getGroupByName("USER");
		
		groupMember.setUsername(registerInfo.getUsername());
		groupMember.setGroup(group);
		
		groupMemberDao.add(groupMember);
		
		return "welcome";
	}
}
