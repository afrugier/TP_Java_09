package fr.pizzeria.ihm.menu.option;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IClientDao;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

/**
 * @author pc Fait la liste des pizzas situer dans le tableau listePizza
 *
 */
public class ListerPizzaOptionMenu implements OptionMenu {

	private static final Logger LOG = LoggerFactory.getLogger(ListerPizzaOptionMenu.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.ihm.OptionMenu#getLibelle()
	 */
	@Override
	public String getLibelle() {
		return "Lister les pizzas";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.ihm.OptionMenu#execute(fr.pizzeria.dao.IPizzaDao)
	 */
	@Override
	public boolean execute(IPizzaDao dao) {
		try {
			for (int i = 0; i < dao.findAllPizzas().size(); i++) {

				if (dao.findAllPizzas().get(i) != null) {

					LOG.info("{} -> {} : Catégorie {}({})", dao.findAllPizzas().get(i).getCode(),
							dao.findAllPizzas().get(i).getNom(),
							dao.findAllPizzas().get(i).getCategoriePizza().getLibelle(),
							dao.findAllPizzas().get(i).getPrix());
				}
			}
		} catch (Exception e) {
			LOG.debug("Error", e);
		}

		LOG.info("");

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.ihm.OptionMenu#getTitle()
	 */
	@Override
	public String getTitle() {
		return "Liste des pizzas";
	}

	@Override
	public boolean execute(IClientDao dao) throws StockageException {
		// pas ici
		return false;
	}

}
