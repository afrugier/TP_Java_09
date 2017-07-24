package fr.pizzeria.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJpa implements IPizzaDao {

	private EntityManagerFactory emf;
	private EntityManager em;

	public PizzaDaoJpa() {
		emf = Persistence.createEntityManagerFactory("pizzerianthony");
	}

	@Override
	public List<Pizza> findAllPizzas() throws SQLException {
		em = emf.createEntityManager();
		List<Pizza> listePizza = em.createNamedQuery("pizza.findAll", Pizza.class).getResultList();
		em.close();
		return listePizza;
	}

	@Override
	public void saveNewPizza(Pizza pizza) throws SavePizzaException {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(pizza);
		em.getTransaction().commit();
		em.close();
	}

	private Pizza findByCode(String codePizza) {
		return em.createNamedQuery("pizza.findByCode", Pizza.class).setParameter("code", codePizza)
				.getSingleResult();
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Pizza p = findByCode(codePizza);
		p.setCode(pizza.getCode());
		p.setNom(pizza.getNom());
		p.setPrix(pizza.getPrix());
		p.setCategoriePizza(pizza.getCategoriePizza());
		em.merge(p);
		em.getTransaction().commit();
		em.close();

	}

	@Override
	public void deletePizza(String codePizza) throws DeletePizzaException {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Pizza p = findByCode(codePizza);
		em.remove(p);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public boolean verifierExistence(String codePizza) throws StockageException {
		em = emf.createEntityManager();
		try {
			em.createNamedQuery("pizza.findByCode", Pizza.class).setParameter("code", codePizza).getSingleResult();
			em.close();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}

	@Override
	public void initPizza() {
		// TODO Auto-generated method stub

	}
}
