package fr.pizzeria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public class PizzaDataBase implements IPizzaDao {
	Connection connection;

	public PizzaDataBase() {
		try {
			connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Pizza> findAllPizzas() {
		try {
			Statement statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveNewPizza(Pizza pizza) throws SavePizzaException {

	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException {

	}

	@Override
	public void deletePizza(String codePizza) throws DeletePizzaException {

	}

	@Override
	public void verifierExistence(String codePizza) throws SavePizzaException {

	}

	@Override
	public void verifierAbsence(String codePizza) throws SavePizzaException {

	}

}
