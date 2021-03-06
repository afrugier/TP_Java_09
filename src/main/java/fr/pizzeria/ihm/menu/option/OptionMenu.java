package fr.pizzeria.ihm.menu.option;

import fr.pizzeria.dao.IClientDao;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

public interface OptionMenu {
	


	/**
	 * Définie les méthodes getLibelle, getTitle et execute
	 */
	public abstract String getLibelle();
	public abstract String getTitle();

	public abstract boolean execute(IPizzaDao dao) throws StockageException;

	public abstract boolean execute(IClientDao dao) throws StockageException;
}
