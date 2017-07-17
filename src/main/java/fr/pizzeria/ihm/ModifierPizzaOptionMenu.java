package fr.pizzeria.ihm;

import java.util.Locale;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class ModifierPizzaOptionMenu implements OptionMenu {

	static Scanner questionAjout = new Scanner(System.in).useLocale(Locale.US);
	private static final Logger LOG = LoggerFactory.getLogger(ModifierPizzaOptionMenu.class);


	/* (non-Javadoc)
	 * @see fr.pizzeria.ihm.OptionMenu#getLibelle()
	 */
	@Override
	public String getLibelle() {
		return "3. Mettre à jour une pizza";
	}


	/* (non-Javadoc)
	 * @see fr.pizzeria.ihm.OptionMenu#execute(fr.pizzeria.dao.IPizzaDao)
	 */
	@Override
	public boolean execute(IPizzaDao dao) {

		LOG.info("Veuillez Choisir la pizza à modifier");

		String codePizza = new ChoixPizza().choice(dao);

		if (!"99".equals(codePizza)) {

			LOG.info("Veuillez saisir le nouveau code");
			String newCodePizza = questionAjout.next();

			LOG.info("Veuillez saisir le nouveau nom (sans espace)");
			String newNomPizza = questionAjout.next();

			LOG.info("Veuillez saisir le nouveau prix");
			double newPrixPizza = questionAjout.nextDouble();
			
			LOG.info("Veuillez saisir la catégorie de la pizza");
			for (CategoriePizza categ : CategoriePizza.values()) {
				String categorie = categ.name();
				LOG.info(categorie);
			}
			String categ = questionAjout.next();

			Pizza pizza = new Pizza(newCodePizza.toUpperCase(), newNomPizza, newPrixPizza,
					CategoriePizza.valueOf(categ.toUpperCase()));

			try {
				dao.updatePizza(codePizza, pizza); 
			} catch (UpdatePizzaException e) {
				LOG.info(e.getMessage());
				LOG.error("Error", e);
			}

			LOG.info("Pizza Modifiée !");
			LOG.info("");
		}

		return false;

	}


	/* (non-Javadoc)
	 * @see fr.pizzeria.ihm.OptionMenu#getTitle()
	 */
	@Override
	public String getTitle() {
		return "Mise à jour d’une pizza";
	}

}
