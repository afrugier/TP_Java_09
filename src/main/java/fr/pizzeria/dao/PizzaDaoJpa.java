package fr.pizzeria.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJpa implements IPizzaDao {
	private EntityManagerFactory emf;
	private EntityManager em;

	public PizzaDaoJpa() {
		emf = Persistence.createEntityManagerFactory("pizzerianthony");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.dao.IPizzaDao#initPizza()
	 */
	@Override
	public void initPizza() {
		em = emf.createEntityManager();
		em.getTransaction().begin();

		List<Pizza> listePizza = new ArrayList<>();

		listePizza.add(new Pizza("FDM", "Fruit de mer", 12.50, CategoriePizza.POISSON));
		listePizza.add(new Pizza("LEG", "La 4 légumes", 14.00, CategoriePizza.VEGETALIENNE));
		listePizza.add(new Pizza("REI", "La reine", 11.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.FROMAGES));
		listePizza.add(new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("ORI", "L'orientale", 13.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("CHA", "Champetre", 14.00, CategoriePizza.VEGETARIENNE));

		for (Pizza pizza : listePizza) {
			em.persist(pizza);
		}
		em.getTransaction().commit();
		em.close();
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
		return em.createNamedQuery("pizza.findByCode", Pizza.class).setParameter("codePizza", codePizza)
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
}
