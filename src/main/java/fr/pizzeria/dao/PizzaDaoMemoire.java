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

	List<Pizza> listePizza = new ArrayList<>();

	/**
	 * initialise le tableau de pizza
	 */
	public PizzaDaoMemoire() {

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
		return listePizza;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.dao.IPizzaDao#saveNewPizza(fr.pizzeria.model.Pizza)
	 */
	public boolean saveNewPizza(Pizza pizza) {

		listePizza.add(new Pizza(pizza.getCode(), pizza.getNom(), pizza.getPrix(), pizza.getCategoriePizza()));

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.dao.IPizzaDao#updatePizza(java.lang.String,
	 * fr.pizzeria.model.Pizza)
	 */
	public boolean updatePizza(String codePizza, Pizza pizza) {

		for (int i = 0; i < listePizza.size(); i++) {
			if (codePizza.equals(listePizza.get(i).getCode())) {
				listePizza.get(i).setCode(pizza.getCode());
				listePizza.get(i).setNom(pizza.getNom());
				listePizza.get(i).setPrix(pizza.getPrix());
				listePizza.get(i).setCategoriePizza(pizza.getCategoriePizza());
				break;
			}
		}

		return false;
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#verifierExistence(java.lang.String)
	 */
	public void verifierExistence(String codePizza) throws SavePizzaException {
		boolean trouve = false;
		for (int i = 0; i < listePizza.size(); i++) {

			if (listePizza.get(i) != null && codePizza.equals(listePizza.get(i).getCode())) {
				trouve = true;
			}
		}
		if (!trouve) {
			throw new SavePizzaException("Le code " + codePizza + " n'existe pas");
		}

	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#verifierAbsence(java.lang.String)
	 */
	public void verifierAbsence(String codePizza) throws SavePizzaException {
		boolean trouve = false;
		for (int i = 0; i < listePizza.size(); i++) {

			if (listePizza.get(i) != null && codePizza.equals(listePizza.get(i).getCode())) {
				trouve = true;
			}
		}
		if (trouve) {
			throw new SavePizzaException("Le code " + codePizza + " existe déja");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.dao.IPizzaDao#deletePizza(java.lang.String)
	 */
	public boolean deletePizza(String codePizza) {

		for (int i = 0; i < listePizza.size(); i++) {
			if (codePizza.equals(listePizza.get(i).getCode())) {
				listePizza.remove(i);
				break;
			}
		}

		return false;
	}

}
