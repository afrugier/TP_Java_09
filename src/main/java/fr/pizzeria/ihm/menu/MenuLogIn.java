package fr.pizzeria.ihm.menu;

import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.ClientDaoJpa;
import fr.pizzeria.dao.IClientDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.ihm.menu.option.Connection;
import fr.pizzeria.ihm.menu.option.Inscription;
import fr.pizzeria.ihm.menu.option.OptionMenu;
import fr.pizzeria.ihm.menu.option.SortirOptionMenu;

public class MenuLogIn {
	private Map<Integer, OptionMenu> optionMenu = new TreeMap<>();
	Scanner questionAjout = new Scanner(System.in).useLocale(Locale.US);
	private static final Logger LOG = LoggerFactory.getLogger(Menu.class);
	static final int NUMERO_OPTION_SORTIE = 99;
	int choixPizza = NUMERO_OPTION_SORTIE;
	private IClientDao dao;

	public MenuLogIn(IClientDao daoClient) {
		this.dao = daoClient;
		initMenu();
	}

	private void initMenu() {
		optionMenu.put(1, new Inscription());
		optionMenu.put(2, new Connection());
		optionMenu.put(NUMERO_OPTION_SORTIE, new SortirOptionMenu());

	}

	public void afficher() {
		// Titre
		LOG.info("***** Pizzeria Client *****");
		optionMenu.forEach((numero, option) -> LOG.info(numero + ". " + option.getLibelle()));
	}

	/**
	 * execute le code en fonction du choix réaliser dans le menu
	 * 
	 * @throws Exception
	 */
	public void manage() throws StockageException {

		dao = new ClientDaoJpa();

		do {
			afficher();
			choixPizza = questionAjout.nextInt();
			optionMenu.get(choixPizza).execute(dao);
		} while (choixPizza != NUMERO_OPTION_SORTIE);

	}

}
