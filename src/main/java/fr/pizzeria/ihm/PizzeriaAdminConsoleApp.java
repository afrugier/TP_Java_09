package fr.pizzeria.ihm;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoJpa;
import fr.pizzeria.ihm.menu.Menu;

/**
 * @author pc
 *
 */
public class PizzeriaAdminConsoleApp {
	/**
	 * @param args
	 * @throws Exception
	 * 
	 */
	public static void main(String[] args) throws Exception {
		IPizzaDao dao = new PizzaDaoJpa();
		dao.initPizza();
		
		Menu menu = new Menu(dao);
		menu.manage();

	}		
}
