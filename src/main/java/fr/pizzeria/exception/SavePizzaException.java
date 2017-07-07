package fr.pizzeria.exception;

public class SavePizzaException extends StockageException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4464726704514516220L;

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

}
