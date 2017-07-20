package fr.pizzeria.dao;

import java.util.List;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

/**
 * @author pc
 *
 */
public interface IPizzaDao {
	/**
	 * @return liste de pizzas
	 */
	List<Pizza> findAllPizzas();
	/**
	 * @param pizza
	 * @return
	 * @throws SavePizzaException
	 */
	void saveNewPizza(Pizza pizza) throws SavePizzaException;
	/**
	 * @param codePizza
	 * @param pizza
	 * @return
	 * @throws UpdatePizzaException
	 */
	void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException;
	/**
	 * @param codePizza
	 * @return
	 * @throws DeletePizzaException
	 */
	void deletePizza(String codePizza) throws DeletePizzaException;
	/**
	 * @param codePizza
	 * @throws SavePizzaException
	 */
	void verifierExistence(String codePizza) throws SavePizzaException;
	/**
	 * @param codePizza
	 * @throws SavePizzaException
	 */
	void verifierAbsence(String codePizza) throws SavePizzaException;
}
