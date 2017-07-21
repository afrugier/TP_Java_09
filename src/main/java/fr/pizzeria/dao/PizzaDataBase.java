package fr.pizzeria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.ihm.menu.option.ListerPizzaOptionMenu;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDataBase implements IPizzaDao {
	private static final Logger LOG = LoggerFactory.getLogger(ListerPizzaOptionMenu.class);
	Connection connection;
	Statement statement;
	PreparedStatement insertPizza;

	@Override
	public void initPizza() {
		try {
			connection = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "");
			statement = connection.createStatement();
			statement.execute("CREATE TABLE `pizza` (`id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,"
					+ "`code` varchar(10), `nom` varchar(255) NOT NULL, `prix` double NOT NULL,"
					+ "`categorie` integer(3) NOT NULL);");

			List<Pizza> listePizza = new ArrayList<>();

			listePizza.add(new Pizza("FDM", "Fruit de mer", 12.50, CategoriePizza.POISSON));
			listePizza.add(new Pizza("LEG", "La 4 légumes", 14.00, CategoriePizza.VEGETALIENNE));
			listePizza.add(new Pizza("REI", "La reine", 11.50, CategoriePizza.VIANDE));
			listePizza.add(new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.FROMAGES));
			listePizza.add(new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
			listePizza.add(new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.VIANDE));
			listePizza.add(new Pizza("ORI", "L'orientale", 13.50, CategoriePizza.VIANDE));
			listePizza.add(new Pizza("CHA", "Champetre", 14.00, CategoriePizza.VEGETARIENNE));
			for (Pizza pizza : listePizza) {
				insertPizza = connection
						.prepareStatement("INSERT INTO pizza(code, nom, prix, categorie) VALUES (?, ?, ?, ?);");
				insertPizza.setString(1, pizza.getCode());
				insertPizza.setString(2, pizza.getNom());
				insertPizza.setDouble(3, pizza.getPrix());
				insertPizza.setString(4, pizza.getCategoriePizza().getLibelle());
				insertPizza.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.debug("Error", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.dao.IPizzaDao#findAllPizzas()
	 */
	@Override
	public List<Pizza> findAllPizzas() throws SQLException {
		List<Pizza> listePizza = new ArrayList<>();
		ResultSet result = statement.executeQuery("SELECT * FROM PIZZA");
		try {
			while (result.next()) {
				String code = result.getString("code");
				String nom = result.getString("nom");
				double prix = result.getDouble("prix");
				int categorie = result.getInt("Categorie");
				listePizza.add(new Pizza(code, nom, prix, CategoriePizza.values()[categorie]));

			}
		} catch (SQLException e) {
			LOG.debug("Error listePizza", e);
		} finally {
			result.close();
		}
		return listePizza;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.dao.IPizzaDao#saveNewPizza(fr.pizzeria.model.Pizza)
	 */
	@Override
	public void saveNewPizza(Pizza pizza) throws SavePizzaException {
		try {
			insertPizza = connection
					.prepareStatement("INSERT INTO pizza(code, nom, prix, categorie) VALUES (?, ?, ?, ?);");
			insertPizza.setString(1, pizza.getCode());
			insertPizza.setString(2, pizza.getNom());
			insertPizza.setDouble(3, pizza.getPrix());
			insertPizza.setInt(4, pizza.getCategoriePizza().ordinal());
			insertPizza.executeUpdate();
		} catch (SQLException e) {
			LOG.debug("Error newPizza", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.dao.IPizzaDao#updatePizza(java.lang.String,
	 * fr.pizzeria.model.Pizza)
	 */
	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException {
		try {
			insertPizza = connection
					.prepareStatement("UPDATE pizza SET code=?, nom=?, prix=?, categorie=? WHERE code=?;");
			insertPizza.setString(1, pizza.getCode());
			insertPizza.setString(2, pizza.getNom());
			insertPizza.setDouble(3, pizza.getPrix());
			insertPizza.setInt(4, pizza.getCategoriePizza().ordinal());
			insertPizza.setString(5, codePizza);
			insertPizza.executeUpdate();
		} catch (SQLException e) {
			LOG.debug("Error updatePizza", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.dao.IPizzaDao#deletePizza(java.lang.String)
	 */
	@Override
	public void deletePizza(String codePizza) throws DeletePizzaException {
		try {
			insertPizza = connection.prepareStatement("DELETE FROM pizza WHERE code=?;");
			insertPizza.setString(1, codePizza);
			insertPizza.executeUpdate();
		} catch (SQLException e) {
			LOG.debug("Error deletePizza", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.dao.IPizzaDao#verifierExistence(java.lang.String)
	 */
	@Override
	public void verifierExistence(String codePizza) throws SavePizzaException {
		// Pas encore fait
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.dao.IPizzaDao#verifierAbsence(java.lang.String)
	 */
	@Override
	public void verifierAbsence(String codePizza) throws SavePizzaException {
		// Pas encore fait
	}

}
