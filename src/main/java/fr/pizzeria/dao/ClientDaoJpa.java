package fr.pizzeria.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.pizzeria.model.Client;

public class ClientDaoJpa implements IClientDao {
	private EntityManagerFactory emf;
	private EntityManager em;

	public ClientDaoJpa() {
		emf = Persistence.createEntityManagerFactory("pizzerianthony");
	}

	@Override
	public List<Client> findAllClient() {
		em = emf.createEntityManager();
		List<Client> listeClient = em.createNamedQuery("client.findAll", Client.class).getResultList();
		em.close();
		return listeClient;
	}

	@Override
	public void saveNewClient(Client client) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(client);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void initClient() {
		// pas ici
	}

	@Override
	public boolean findOneClient(String emailClient, String mdp) {
		em = emf.createEntityManager();
		boolean client = em.createNamedQuery("client.findById", Client.class).setParameter("email", emailClient)
				.setParameter("mdp", mdp) != null;
		em.close();
		return client;
	}

}
