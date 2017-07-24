package fr.pizzeria.ihm;

import java.sql.SQLException;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDataBase;
import fr.pizzeria.ihm.menu.Menu;

/**
 * @author pc
 *
 */
public class PizzeriaAdminConsoleApp {
	/**
	 * @param args
	 * @throws SQLException
	 * 
	 */
	public static void main(String[] args) throws SQLException {
		IPizzaDao dao = new PizzaDataBase();
		dao.initPizza();
		
		Menu menu = new Menu(dao);
		menu.manage();

	}		
}
