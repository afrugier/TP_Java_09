package fr.pizzeria.exception;

public class StockageException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6615343078607224741L;

	/**
	 * Gére les exceptions de Stockage de pizza
	 */
	public StockageException() {
		super();
	}

	/**
	 * @param message
	 * Gére les exceptions de Stockage de pizza en envoyant un message
	 */
	public StockageException(String message) {
		super(message);
	}

}
