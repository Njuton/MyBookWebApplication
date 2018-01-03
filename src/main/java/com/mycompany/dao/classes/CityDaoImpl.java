package com.mycompany.dao.classes;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.dao.interfaces.CityDao;
import com.mycompany.db.entity.City;

/**
 * Реализация Dao-интерфейса {@link com.mycompany.dao.interfaces.CityDao}
 */
@Repository
@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
public class CityDaoImpl extends PersistenceDao<City, Integer> implements CityDao{
	@Override
	public City getCityByName(String name) {
		return (City) em.createQuery("SELECT c FROM City c WHERE c.name='" + name + "'").getSingleResult();
	}
}
