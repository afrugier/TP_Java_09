package fr.pizzeria.ihm;

import java.util.Locale;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.SavePizzaException;

public class ChoixPizza {
	static Scanner questionAjout = new Scanner(System.in).useLocale(Locale.US);
	private static final Logger LOG = LoggerFactory.getLogger(ChoixPizza.class);

	public String choice(IPizzaDao dao) {
		LOG.info("(99 pour abandonner)");

		String codePizza = null;
		boolean codeTrouve = false;

		do {
			codePizza = questionAjout.next();
			try {
				dao.verifierExistence(codePizza);
				codeTrouve = true;
			} catch (SavePizzaException e) {
				LOG.info("Le code " + codePizza + " n'existe pas");
				SavePizzaException.executer(e);
				codeTrouve = false;
			}
		} while (!codeTrouve);

		return codePizza;
	}

}
