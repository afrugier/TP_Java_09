package fr.pizzeria.ihm;

import fr.pizzeria.dao.ClientDaoJpa;
import fr.pizzeria.dao.IClientDao;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoJpa;
import fr.pizzeria.ihm.menu.MenuLogIn;

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
		IPizzaDao daoPizza = new PizzaDaoJpa();
		daoPizza.initPizza();

		IClientDao daoClient = new ClientDaoJpa();
		daoClient.initClient();
		
		MenuLogIn menu = new MenuLogIn(daoClient);
		menu.manage();

	}		
}
