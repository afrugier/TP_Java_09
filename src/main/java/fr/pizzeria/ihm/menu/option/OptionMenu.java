package fr.pizzeria.ihm.menu.option;

import java.sql.SQLException;

import fr.pizzeria.dao.IPizzaDao;

public interface OptionMenu {
	


	/**
	 * Définie les méthodes getLibelle, getTitle et execute
	 */
	public abstract String getLibelle();
	public abstract String getTitle();

	public abstract boolean execute(IPizzaDao dao) throws SQLException;
}
