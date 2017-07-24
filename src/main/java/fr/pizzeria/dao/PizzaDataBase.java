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
	private static final String CREATE_TABLE = "CREATE TABLE pizza (`id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,code varchar(10), "
			+ "nom varchar(255) NOT NULL, prix double NOT NULL, categorie integer(3) NOT NULL);";
	private static final String INSERT_LIGNE = "INSERT INTO pizza(code, nom, prix, categorie) VALUES (?, ?, ?, ?);";
	private static final String UPDATE_LIGNE = "UPDATE pizza SET code=?, nom=?, prix=?, categorie=? WHERE code=?;";
	private static final String DELETE_LIGNE = "DELETE FROM pizza WHERE code=?;";
	private static final String FIND_PIZZA = "SELECT * FROM pizza WHERE code=?;";

	private Connection createConnexion() throws SQLException {
		return DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "");
	}

	private void insertLigne(Connection connection, List<Pizza> listePizza) throws SQLException {
		for (Pizza pizza : listePizza) {
			try (PreparedStatement queryPizza = connection.prepareStatement(INSERT_LIGNE);) {
				queryPizza.setString(1, pizza.getCode());
				queryPizza.setString(2, pizza.getNom());
				queryPizza.setDouble(3, pizza.getPrix());
				queryPizza.setInt(4, pizza.getCategoriePizza().ordinal());
				queryPizza.executeUpdate();

			}
		}
	}

	private void createTable(Connection connection) throws SQLException {
		try (PreparedStatement createPizza = connection.prepareStatement(CREATE_TABLE);) {
			createPizza.execute();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.dao.IPizzaDao#initPizza()
	 */
	@Override
	public void initPizza() {
		try (Connection connection = createConnexion();) {
			createTable(connection);

			List<Pizza> listePizza = new ArrayList<>();

			listePizza.add(new Pizza("FDM", "Fruit de mer", 12.50, CategoriePizza.POISSON));
			listePizza.add(new Pizza("LEG", "La 4 légumes", 14.00, CategoriePizza.VEGETALIENNE));
			listePizza.add(new Pizza("REI", "La reine", 11.50, CategoriePizza.VIANDE));
			listePizza.add(new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.FROMAGES));
			listePizza.add(new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
			listePizza.add(new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.VIANDE));
			listePizza.add(new Pizza("ORI", "L'orientale", 13.50, CategoriePizza.VIANDE));
			listePizza.add(new Pizza("CHA", "Champetre", 14.00, CategoriePizza.VEGETARIENNE));
			insertLigne(connection, listePizza);
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
		try (Connection connection = createConnexion();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery("SELECT * FROM PIZZA");) {

			while (result.next()) {
				String code = result.getString("code");
				String nom = result.getString("nom");
				double prix = result.getDouble("prix");
				int categorie = result.getInt("Categorie");
				listePizza.add(new Pizza(code, nom, prix, CategoriePizza.values()[categorie]));

			}
		} catch (SQLException e) {
			LOG.debug("Error listePizza", e);
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
			PreparedStatement queryPizza = createConnexion().prepareStatement(INSERT_LIGNE);
			queryPizza.setString(1, pizza.getCode());
			queryPizza.setString(2, pizza.getNom());
			queryPizza.setDouble(3, pizza.getPrix());
			queryPizza.setInt(4, pizza.getCategoriePizza().ordinal());
			queryPizza.executeUpdate();
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
			PreparedStatement queryPizza = createConnexion().prepareStatement(UPDATE_LIGNE);
			queryPizza.setString(1, pizza.getCode());
			queryPizza.setString(2, pizza.getNom());
			queryPizza.setDouble(3, pizza.getPrix());
			queryPizza.setInt(4, pizza.getCategoriePizza().ordinal());
			queryPizza.setString(5, codePizza);
			queryPizza.executeUpdate();
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
			PreparedStatement queryPizza = createConnexion().prepareStatement(DELETE_LIGNE);
			queryPizza.setString(1, codePizza);
			queryPizza.executeUpdate();
		} catch (SQLException e) {
			LOG.debug("Error deletePizza", e);
		}
	}

	private boolean existence(String codePizza) throws SQLException {
		try (Connection connection = createConnexion();
                PreparedStatement findPizza = connection.prepareStatement(FIND_PIZZA);
                ResultSet result = findPizza.executeQuery();) {
            findPizza.setString(1, codePizza);
			return result.first();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.dao.IPizzaDao#verifierExistence(java.lang.String)
	 */
	@Override
	public boolean verifierExistence(String codePizza) throws SavePizzaException, SQLException {
		return existence(codePizza);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.dao.IPizzaDao#verifierAbsence(java.lang.String)
	 */
	@Override
	public boolean verifierAbsence(String codePizza) throws SavePizzaException, SQLException {
		return existence(codePizza);
	}

}
