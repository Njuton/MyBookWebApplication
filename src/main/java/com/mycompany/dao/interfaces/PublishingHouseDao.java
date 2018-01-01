package com.mycompany.dao.interfaces;

import com.mycompany.db.entity.PublishingHouse;

public interface PublishingHouseDao extends GenericDao<PublishingHouse, Integer>{
	
	/**
	 * Издательство по названию
	 */
	
	PublishingHouse getPublishingHouseByName(String name);

}
