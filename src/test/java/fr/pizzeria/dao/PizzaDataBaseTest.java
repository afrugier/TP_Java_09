package fr.pizzeria.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.ihm.menu.option.AjouterPizzaOptionMenu;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDataBaseTest {
	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
	static Connection connection;
	static Statement statement;
	private static final Logger LOG = LoggerFactory.getLogger(AjouterPizzaOptionMenu.class);

	static List<Pizza> listePizza;
	static PreparedStatement insertPizza;

	@BeforeClass
	public static void setUp() throws Exception {
		connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
		statement = connection.createStatement();

		statement.execute("CREATE TABLE `pizza` (`id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,"
				+ "`code` varchar(10), `nom` varchar(255) NOT NULL, `prix` double NOT NULL,"
				+ "`categorie` varchar(255) NOT NULL);");

		listePizza = new ArrayList<>();

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
	}

	@Test
	public void testConnection() throws Exception {
		assertThat(connection.isValid(0)).isEqualTo(true);
	}

	@Test
	public void testFindAllPizzasBDD() throws Exception {
		ResultSet resultats = statement.executeQuery("SELECT * FROM PIZZA");
		while (resultats.next()) {
			assertThat(resultats.getString("code")).isIn("FDM", "LEG", "REI", "FRO", "CAN", "SAV", "ORI", "CHA");
			assertThat(resultats.getString("nom")).isIn("Fruit de mer", "La 4 légumes", "La reine", "La 4 fromages",
					"La cannibale", "La savoyarde", "L'orientale", "Champetre");
			assertThat(resultats.getDouble("prix")).isIn(12.50, 14.00, 11.50, 12.00, 13.00, 13.50);
			assertThat(resultats.getString("categorie")).isIn("Poisson", "Fromages", "Végetarienne", "Viande",
					"Végetalienne");
		}
	}

	@Test
	public void testSaveNewPizza() throws Exception {

		insertPizza = connection.prepareStatement("INSERT INTO pizza(code, nom, prix, categorie) VALUES (?, ?, ?, ?);");
		insertPizza.setString(1, "CAL");
		insertPizza.setString(2, "Calzone");
		insertPizza.setDouble(3, 12.50);
		insertPizza.setString(4, "Viande");
		insertPizza.executeUpdate();

		ResultSet resultats = statement.executeQuery("SELECT * FROM pizza WHERE code = 'CAL'; ");
		resultats.next();
		assertThat(resultats.getString("code")).isEqualTo("CAL");
		assertThat(resultats.getString("nom")).isEqualTo("Calzone");
		assertThat(resultats.getDouble("prix")).isEqualTo(12.50);
		assertThat(resultats.getString("categorie")).isEqualTo("Viande");

	}

	@Test
	public void testUpdatePizza() throws Exception {
		insertPizza = connection.prepareStatement("UPDATE pizza SET code=?, nom=?, prix=?, categorie=? WHERE code=?;");
		insertPizza.setString(1, "CAL");
		insertPizza.setString(2, "Calzone");
		insertPizza.setDouble(3, 12.50);
		insertPizza.setString(4, "Viande");
		insertPizza.setString(5, "LEG");
		insertPizza.executeUpdate();

		ResultSet resultats = statement.executeQuery("SELECT * FROM pizza WHERE code = 'CAL'; ");
		resultats.next();
		assertThat(resultats.getString("code")).isEqualTo("CAL");
		assertThat(resultats.getString("nom")).isEqualTo("Calzone");
		assertThat(resultats.getDouble("prix")).isEqualTo(12.50);
		assertThat(resultats.getString("categorie")).isEqualTo("Viande");

		ResultSet resultat = statement.executeQuery("SELECT * FROM pizza WHERE code = 'LEG';");
		while (resultat.next()) {
			assertThat(resultat.getString("code")).isNotEqualTo("LEG");
		}
	}

	@Test
	public void testDeletePizza() throws Exception {
		insertPizza = connection.prepareStatement("DELETE FROM pizza WHERE code=?;");
		insertPizza.setString(1, "LEG");
		insertPizza.executeUpdate();


		ResultSet resultats = statement.executeQuery("SELECT * FROM pizza WHERE code = 'LEG';");
		while (resultats.next()) {
			assertThat(resultats.getString("code")).isNotEqualTo("LEG");
		}
	}
}
