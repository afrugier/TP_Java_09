package fr.pizzeria.exception;

public class UpdatePizzaException extends StockageException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9181053071332463432L;

	/**
	 * Gére les exceptions de Mise a jour de pizza
	 */
	public UpdatePizzaException() {
		super();
	}

	/**
	 * @param message
	 * Gére les exceptions de Mise a jour de pizza en envoyent un message
	 */
	public UpdatePizzaException(String message) {
		super(message);
	}
}
