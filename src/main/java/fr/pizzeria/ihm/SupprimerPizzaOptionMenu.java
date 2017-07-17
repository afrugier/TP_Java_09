package fr.pizzeria.ihm;

import java.util.Locale;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DeletePizzaException;

public class SupprimerPizzaOptionMenu implements OptionMenu {

	static Scanner questionAjout = new Scanner(System.in).useLocale(Locale.US);
	private static final Logger LOG = LoggerFactory.getLogger(SupprimerPizzaOptionMenu.class);

	/* (non-Javadoc)
	 * @see fr.pizzeria.ihm.OptionMenu#getLibelle()
	 */
	@Override
	public String getLibelle() {
		return "4. Supprimer une pizza";
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.ihm.OptionMenu#execute(fr.pizzeria.dao.IPizzaDao)
	 */
	@Override
	public boolean execute(IPizzaDao dao) {

		LOG.info("Veuillez Choisir la pizza à supprimer");
		
		ChoixPizza cp = new ChoixPizza();
		String codePizza = cp.choice(dao);
		
		
		if (!codePizza.equals("99")) {

			try {
				dao.deletePizza(codePizza);
			} catch (DeletePizzaException e) {
				LOG.info(e.getMessage());
				LOG.error("Error", e);
			}
			
			LOG.info("Pizza Supprimée !");
			LOG.info("");

		}
		return false;
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.ihm.OptionMenu#getTitle()
	 */
	@Override
	public String getTitle() {
		return "Suppression d’une pizza";
	}

}
