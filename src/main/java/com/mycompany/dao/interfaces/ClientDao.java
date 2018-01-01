package com.mycompany.dao.interfaces;

import com.mycompany.db.entity.Client;

public interface ClientDao extends GenericDao<Client, Integer>{
	
	/**
	 * Получить клиента по имени, фамилии, мобильному (однозначная идентификация клиента)
	 */
	Client getClientByParams(String firstName, String lastName, String mobileTelephone);

}
