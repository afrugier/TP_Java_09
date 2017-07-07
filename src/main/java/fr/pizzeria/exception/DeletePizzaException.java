package fr.pizzeria.exception;

/**
 * @author pc
 *
 */
public class DeletePizzaException extends StockageException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2149448715820606009L;

	/**
	 * Gére les exceptions de suppression de pizza
	 */
	public DeletePizzaException() {
		super();
	}

	/**
	 * @param message
	 * Gére les exceptions de suppression de pizza en envoyant un message
	 */
	public DeletePizzaException(String message) {
		super(message);
	}
}
