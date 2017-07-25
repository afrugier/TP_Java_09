package fr.pizzeria.ihm.menu.option;

import java.util.Locale;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IClientDao;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.ihm.ChoixPizza;

public class SupprimerPizzaOptionMenu implements OptionMenu {

	Scanner questionAjout = new Scanner(System.in).useLocale(Locale.US);
	private static final Logger LOG = LoggerFactory.getLogger(SupprimerPizzaOptionMenu.class);

	/* (non-Javadoc)
	 * @see fr.pizzeria.ihm.OptionMenu#getLibelle()
	 */
	@Override
	public String getLibelle() {
		return "Supprimer une pizza";
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.ihm.OptionMenu#execute(fr.pizzeria.dao.IPizzaDao)
	 */
	@Override
	public boolean execute(IPizzaDao dao) throws StockageException {

		LOG.info("Veuillez Choisir la pizza à supprimer");
		
		String codePizza = new ChoixPizza().choice(dao);
		
		
		if (!"99".equals(codePizza)) {

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

	@Override
	public boolean execute(IClientDao dao) throws StockageException {
		// pas ici
		return false;
	}

}
