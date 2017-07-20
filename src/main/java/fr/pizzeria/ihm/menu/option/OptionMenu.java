package fr.pizzeria.ihm.menu.option;

import fr.pizzeria.dao.IPizzaDao;

public interface OptionMenu {
	


	/**
	 * Définie les méthodes getLibelle, getTitle et execute
	 */
	public abstract String getLibelle();
	public abstract String getTitle();

	public abstract boolean execute(IPizzaDao dao);
}
