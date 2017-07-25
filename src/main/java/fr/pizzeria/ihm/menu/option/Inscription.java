package fr.pizzeria.ihm.menu.option;

import fr.pizzeria.dao.IClientDao;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

public class Inscription implements OptionMenu {

	@Override
	public String getLibelle() {
		return "S'inscrire";
	}

	@Override
	public String getTitle() {
		return "Veuillez vous inscrire";
	}


	@Override
	public boolean execute(IClientDao dao) throws StockageException {
		return false;
	}

	@Override
	public boolean execute(IPizzaDao dao) throws StockageException {
		// pas ici
		return false;
	}
}
