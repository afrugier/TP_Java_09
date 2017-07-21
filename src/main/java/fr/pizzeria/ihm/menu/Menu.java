package fr.pizzeria.ihm.menu;

import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDataBase;
import fr.pizzeria.ihm.menu.option.AjouterPizzaOptionMenu;
import fr.pizzeria.ihm.menu.option.ListerPizzaOptionMenu;
import fr.pizzeria.ihm.menu.option.ModifierPizzaOptionMenu;
import fr.pizzeria.ihm.menu.option.OptionMenu;
import fr.pizzeria.ihm.menu.option.SortirOptionMenu;
import fr.pizzeria.ihm.menu.option.SupprimerPizzaOptionMenu;

/**
 * @author pc
 *
 */
public class Menu {
	private Map<Integer, OptionMenu> optionMenu = new TreeMap<>();
	Scanner questionAjout = new Scanner(System.in).useLocale(Locale.US);
	private static final Logger LOG = LoggerFactory.getLogger(Menu.class);
	static final int NUMERO_OPTION_SORTIE = 99;
	int choixPizza = NUMERO_OPTION_SORTIE;
	private IPizzaDao dao;

	public Menu(IPizzaDao dao) {
		this.dao = dao;
		initMenu();

	}

	private void initMenu() {
		optionMenu.put(1, new ListerPizzaOptionMenu());
		optionMenu.put(2, new AjouterPizzaOptionMenu());
		optionMenu.put(3, new ModifierPizzaOptionMenu());
		optionMenu.put(4, new SupprimerPizzaOptionMenu());
		optionMenu.put(NUMERO_OPTION_SORTIE, new SortirOptionMenu());

	}

	public void afficher() {
		// Titre
		LOG.info("***** Pizzeria Administration *****");
		optionMenu.forEach((numero, option) -> LOG.info(numero + ". " + option.getLibelle()));
	}

	/**
	 * execute le code en fonction du choix réaliser dans le menu
	 */
	public void manage() {

		dao = new PizzaDataBase();

		do {
			afficher();
			choixPizza = questionAjout.nextInt();
			optionMenu.get(choixPizza).execute(dao);
		} while (choixPizza != NUMERO_OPTION_SORTIE);

	}
}
