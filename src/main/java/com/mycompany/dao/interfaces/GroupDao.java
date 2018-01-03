package com.mycompany.dao.interfaces;

import com.mycompany.db.entity.security.Group;

public interface GroupDao extends GenericDao<Group, Long>{
	
	/**
	 * Получить группу по её имени
	 */
	Group getGroupByName(String name);

}
