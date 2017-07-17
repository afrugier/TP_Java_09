package fr.pizzeria.ihm;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.PizzaDaoMemoire;

/**
 * @author pc
 *
 */
public class Menu {
	static Map<Integer, OptionMenu> optionMenu = new HashMap<>();
	static Scanner questionAjout = new Scanner(System.in).useLocale(Locale.US);
	private static final Logger LOG = LoggerFactory.getLogger(Menu.class);

	public Menu() {

		optionMenu.put(Integer.valueOf(0), new ListerPizzaOptionMenu());
		optionMenu.put(Integer.valueOf(1), new AjouterPizzaOptionMenu());
		optionMenu.put(Integer.valueOf(2), new ModifierPizzaOptionMenu());
		optionMenu.put(Integer.valueOf(3), new SupprimerPizzaOptionMenu());
	}

	/**
	 * execute le code en fonction du choix réaliser dans le menu
	 */
	public void manage() {

		PizzaDaoMemoire dao = new PizzaDaoMemoire();

		int choixPizza = 0;
		do {
			afficher();
			choixPizza = questionAjout.nextInt();

			switch (choixPizza) {
			/* case 1 : Affiche la liste des pizzas */
			case 1:
				LOG.info(optionMenu.get(0).getTitle());
				optionMenu.get(0).execute(dao);
				break;
			/* case 2 : Permet l'ajout d'une pizza */
			case 2:
				LOG.info(optionMenu.get(1).getTitle());
				optionMenu.get(1).execute(dao);
				break;
			/* case 3 : Permet de modifier un pizza */
			case 3:
				LOG.info(optionMenu.get(2).getTitle());
				optionMenu.get(0).execute(dao);
				optionMenu.get(2).execute(dao);
				break;
			/* case 4 : Permet de supprimer une Pizza */
			case 4:
				LOG.info(optionMenu.get(3).getTitle());
				optionMenu.get(0).execute(dao);
				optionMenu.get(3).execute(dao);
				break;
			/* Permet de sortir de l'application */
			case 99:
				LOG.info("Aurevoir :-(");
				break;
			default:
				break;
			}
		} while (choixPizza != 99);
	}

	/**
	 * Affiche le menu
	 */
	public void afficher() {
		// Titre
		LOG.info("***** Pizzeria Administration *****");

		for (int i = 0; i < optionMenu.size(); i++) {
			LOG.info(optionMenu.get(i).getLibelle());
		}

		LOG.info("99. Sortir");

	}
}
