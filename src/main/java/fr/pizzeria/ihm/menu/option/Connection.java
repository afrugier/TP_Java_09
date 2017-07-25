package fr.pizzeria.ihm.menu.option;

import java.util.Locale;
import java.util.Scanner;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IClientDao;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

public class Connection implements OptionMenu {

	Scanner questionAjout = new Scanner(System.in).useLocale(Locale.US);
	private static final Logger LOG = LoggerFactory.getLogger(Connection.class);

	@Override
	public String getLibelle() {
		return "Se connecter";
	}

	@Override
	public String getTitle() {
		return "Veuillez rentrez vos identifiants";
	}

	@Override
	public boolean execute(IClientDao dao) throws StockageException {
		LOG.info("Veuillez saisir votre email");
		String id = questionAjout.next();
		LOG.info("Veuillez saisir votre mot de passe");
		String mdp = DigestUtils.md5Hex(questionAjout.next());

		boolean client = dao.findOneClient(id, mdp);
		if (!client) {
			LOG.info("Vous etes connecté");
			return true;
		} else {
			LOG.info("Identifiants erroné");
			return false;
		}


	}

	@Override
	public boolean execute(IPizzaDao dao) throws StockageException {
		// pas ici
		return false;
	}

}
