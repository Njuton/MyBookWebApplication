package com.mycompany.dao.classes;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.dao.interfaces.PublishingHouseDao;
import com.mycompany.db.entity.PublishingHouse;

/**
 * Реализация Dao-интерфейса {@link com.mycompany.dao.interfaces.PublishingHouseDao}
 */
@Repository
@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
public class PublishingHouseDaoImpl extends PersistenceDao<PublishingHouse, Integer> implements PublishingHouseDao{

	@Override
	public PublishingHouse getPublishingHouseByName(String name) {
		String query = "SELECT ph FROM PublishingHouse ph WHERE ph.name=" + "'" + name + "'";
		return (PublishingHouse) em.createQuery(query).getSingleResult();
	}
	
}
