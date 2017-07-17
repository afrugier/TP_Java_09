package fr.pizzeria.ihm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;

/**
 * @author pc Fait la liste des pizzas situer dans le tableau listePizza
 *
 */
public class ListerPizzaOptionMenu extends OptionMenu {

	private static final Logger LOG = LoggerFactory.getLogger(ListerPizzaOptionMenu.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.ihm.OptionMenu#getLibelle()
	 */
	@Override
	public String getLibelle() {
		return "1. Lister les pizzas";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.ihm.OptionMenu#execute(fr.pizzeria.dao.IPizzaDao)
	 */
	@Override
	public boolean execute(IPizzaDao dao) {
		for (int i = 0; i < dao.findAllPizzas().size(); i++) {

			if (dao.findAllPizzas().get(i) != null) {

				LOG.info("{} -> {} : Catégorie {}({})", dao.findAllPizzas().get(i).getCode(),
						dao.findAllPizzas().get(i).getNom(),
						dao.findAllPizzas().get(i).getCategoriePizza().getLibelle(),
						dao.findAllPizzas().get(i).getPrix());
			}
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

}
