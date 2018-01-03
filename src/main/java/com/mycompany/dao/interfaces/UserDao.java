package com.mycompany.dao.interfaces;

import com.mycompany.db.entity.security.User;

public interface UserDao extends GenericDao<User, String>{
	
	User getUserByName(String name);
}
