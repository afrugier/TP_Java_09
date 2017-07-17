package fr.pizzeria.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SavePizzaException extends StockageException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4464726704514516220L;

	private static final Logger LOG = LoggerFactory.getLogger(SavePizzaException.class);

	/**
	 * Gére les exceptions de sauvegarde de pizza
	 */
	public SavePizzaException() {
		super();
	}

	/**
	 * @param message
	 * Gére les exceptions de sauvegarde de pizza en envoyant un message
	 */
	public SavePizzaException(String message) {
		super(message);
	}

	public static void executer(SavePizzaException e) {
		LOG.debug("Erreur lors de la recherche d'un code pizza dans la classe", e);

	}

}
