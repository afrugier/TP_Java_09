package fr.pizzeria.ihm;

import java.util.Locale;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.ihm.menu.option.ListerPizzaOptionMenu;
import fr.pizzeria.ihm.menu.option.SortirOptionMenu;

public class ChoixPizza {

	Scanner questionAjout = new Scanner(System.in).useLocale(Locale.US);
	private static final Logger LOG = LoggerFactory.getLogger(ChoixPizza.class);
	ListerPizzaOptionMenu listePizza = new ListerPizzaOptionMenu();
	SortirOptionMenu sortir = new SortirOptionMenu();

	public String choice(IPizzaDao dao) throws StockageException {
		LOG.info("(99 pour abandonner)");
		LOG.info("");
		listePizza.execute(dao);

		String codePizza = null;
		boolean codeTrouve = false;

		do {
			codePizza = questionAjout.next();
			try {
				if ("99".equals(codePizza)) {
					sortir.execute(dao);
				} else {
					dao.verifierExistence(codePizza);
					codeTrouve = true;
				}
			} catch (SavePizzaException e) {
				LOG.info("Le code " + codePizza + " n'existe pas");
				SavePizzaException.executer(e);
				codeTrouve = false;
			}
		} while (!codeTrouve);

		return codePizza;
	}

}
