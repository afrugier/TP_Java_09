package fr.pizzeria.dao;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoMemoireTest {
	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
	private PizzaDaoMemoire pizzaDaoMemoire;

	List<Pizza> listePizza;

	@Before
	public void setUp() throws Exception {
		pizzaDaoMemoire = new PizzaDaoMemoire();
		listePizza = new ArrayList<>();
		listePizza.add(new Pizza("FDM", "Fruit de mer", 12.50, CategoriePizza.POISSON));
		listePizza.add(new Pizza("LEG", "La 4 légumes", 14.00, CategoriePizza.VEGETALIENNE));
		listePizza.add(new Pizza("REI", "La reine", 11.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.FROMAGES));
		listePizza.add(new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("ORI", "L'orientale", 13.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("CHA", "Champetre", 14.00, CategoriePizza.VEGETARIENNE));
	}

	@Test
	public void testfindAllPizzas() throws Exception {
		assertThat(listePizza).containsAll(pizzaDaoMemoire.findAllPizzas());
	}

	@Test
	public void testsaveNewPizza() throws Exception {
		Pizza pizza = new Pizza("CAL", "Calzone", 12.50, CategoriePizza.VIANDE);
		pizzaDaoMemoire.saveNewPizza(pizza);
		assertThat(pizzaDaoMemoire.findAllPizzas()).contains(pizza);

	}

	@Test
	public void testupdatePizza() throws Exception {
		Pizza pizza = new Pizza("CAL", "Calzone", 10, CategoriePizza.VIANDE);
		pizzaDaoMemoire.updatePizza("FDM", pizza);
		assertThat(pizzaDaoMemoire.findAllPizzas()).contains(pizza);

	}

	@Test
	public void testdeletePizza() throws Exception {
		Pizza pizza = new Pizza("FDM", "Fruit de mer", 12.50, CategoriePizza.POISSON);
		pizzaDaoMemoire.deletePizza("FDM");
		assertThat(pizzaDaoMemoire.findAllPizzas()).doesNotContain(pizza);

	}
}
