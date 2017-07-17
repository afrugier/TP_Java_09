package fr.pizzeria.ihm;

import java.util.Locale;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class AjouterPizzaOptionMenu implements OptionMenu {

	static Scanner questionAjout = new Scanner(System.in).useLocale(Locale.US);

	private static final Logger LOG = LoggerFactory.getLogger(AjouterPizzaOptionMenu.class);
	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.ihm.OptionMenu#getLibelle()
	 */
	@Override
	public String getLibelle() {
		return "2. Ajouter une nouvelle pizza";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.ihm.OptionMenu#execute(fr.pizzeria.dao.IPizzaDao)
	 */
	@Override
	public boolean execute(IPizzaDao dao) {

		LOG.info("Veuillez saisir le code");

		String codePizza = null;
		boolean codeTrouve = false;

		do {
			codePizza = questionAjout.next();
			try {
				dao.verifierAbsence(codePizza.toUpperCase());
				codeTrouve = true;
			} catch (SavePizzaException e) {
				LOG.info(e.getMessage());
				SavePizzaException.executer(e);
				codeTrouve = false;
			}
		} while (!codeTrouve);

		LOG.info("Veuillez saisir le nom (sans espace)");
		String nomPizza = questionAjout.next();

		LOG.info("Veuillez saisir le prix");
		double prixPizza = questionAjout.nextDouble();

		LOG.info("Veuillez saisir la catégorie de la pizza");
		for (CategoriePizza categ : CategoriePizza.values()) {
			String categorie = categ.name();
			LOG.info("{}", categorie);
		}
		String categ = questionAjout.next();

		/*Enregistrement de la nouvelle Pizza*/
		Pizza pizza = new Pizza(codePizza.toUpperCase(), nomPizza, prixPizza,
				CategoriePizza.valueOf(categ.toUpperCase()));

		try {
			dao.saveNewPizza(pizza);
		} catch (SavePizzaException e) {
			LOG.info(e.getMessage());
			LOG.error("Error : ", e);
		}

		LOG.info("Pizza Ajouté !");
		LOG.info("");

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.ihm.OptionMenu#getTitle()
	 */
	@Override
	public String getTitle() {
		return "Ajout d’une nouvelle pizza";
	}
}
