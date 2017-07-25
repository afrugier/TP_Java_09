package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * @author pc
 *
 */
public class PizzaDaoMemoire implements IPizzaDao {

	List<Pizza> listePizza;

	/**
	 * initialise le tableau de pizza
	 */
	public PizzaDaoMemoire() {

		listePizza = new ArrayList<>();

		listePizza.add(new Pizza("FDM", "Fruit de mer", 12.50, CategoriePizza.POISSON));
		listePizza.add(new Pizza("LEG", "La 4 légumes", 14.00, CategoriePizza.VEGETALIENNE));
		listePizza.add(new Pizza("REI", "La reine", 11.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.FROMAGES));
		listePizza.add(new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("ORI", "L'orientale", 13.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("CHA", "Champetre", 14.00, CategoriePizza.VEGETARIENNE));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.dao.IPizzaDao#findAllPizzas()
	 */
	public List<Pizza> findAllPizzas() {
		return new ArrayList<>(listePizza);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.dao.IPizzaDao#saveNewPizza(fr.pizzeria.model.Pizza)
	 */
	public void saveNewPizza(Pizza pizza) throws SavePizzaException {

		listePizza.add(new Pizza(pizza.getCode(), pizza.getNom(), pizza.getPrix(), pizza.getCategoriePizza()));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.dao.IPizzaDao#updatePizza(java.lang.String,
	 * fr.pizzeria.model.Pizza)
	 */
	public void updatePizza(String codePizza, Pizza pizza) {

		for (Pizza p : listePizza) {
			if (codePizza.equals(p.getCode())) {
				p.setCode(pizza.getCode());
				p.setNom(pizza.getNom());
				p.setPrix(pizza.getPrix());
				p.setCategoriePizza(pizza.getCategoriePizza());
				break;
			}
		}

	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#verifierExistence(java.lang.String)
	 */
	public boolean verifierExistence(String codePizza) throws SavePizzaException {
		boolean trouve = false;
		for (Pizza p : listePizza) {

			if (p != null && codePizza.equals(p.getCode())) {
				trouve = true;
			}
		}
		if (!trouve) {
			throw new SavePizzaException("Le code " + codePizza + " n'existe pas");
		}
		return trouve;

	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#verifierAbsence(java.lang.String)
	 */
	public boolean verifierAbsence(String codePizza) throws SavePizzaException {
		boolean trouve = false;
		for (int i = 0; i < listePizza.size(); i++) {

			if (listePizza.get(i) != null && codePizza.equals(listePizza.get(i).getCode())) {
				trouve = true;
			}
		}
		if (trouve) {
			throw new SavePizzaException("Le code " + codePizza + " existe déja");
		}
		return trouve;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.dao.IPizzaDao#deletePizza(java.lang.String)
	 */
	public void deletePizza(String codePizza) {

		for (Pizza p : listePizza) {
			if (codePizza.equals(p.getCode())) {
				listePizza.remove(p);
				break;
			}
		}
	}

	@Override
	public void initPizza() {
		// Pas d'init ici

	}
}
