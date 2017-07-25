package fr.pizzeria.dao;

import java.util.List;

import fr.pizzeria.model.Client;

public interface IClientDao {
	/**
	 * @return
	 */
	List<Client> findAllClient();

	/**
	 * @param client
	 */
	void saveNewClient(Client client);

	/**
	 * @param emailClient
	 * @return
	 */
	boolean findOneClient(String emailClient, String mdp);

	/**
	 * 
	 */
	void initClient();
}
