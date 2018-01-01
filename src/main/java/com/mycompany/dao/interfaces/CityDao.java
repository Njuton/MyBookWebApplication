package com.mycompany.dao.interfaces;

import com.mycompany.db.entity.City;

public interface CityDao extends GenericDao<City, Integer>{
	
	/**
	 * Получить город по его названию
	 */
	City getCityByName(String name);

}
