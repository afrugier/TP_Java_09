package fr.pizzeria.ihm;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import fr.pizzeria.ihm.menu.Menu;

public class MenuTest {
	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
	private Menu menu;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAfficher() throws Exception {

		menu = new Menu();
		menu.afficher();
		assertThat(systemOutRule.getLog()).contains("***** Pizzeria Administration *****");
		assertThat(systemOutRule.getLog()).contains("1. Lister les pizzas");
		assertThat(systemOutRule.getLog()).contains("2. Ajouter une nouvelle pizza");
		assertThat(systemOutRule.getLog()).contains("3. Mettre à jour une pizza");
		assertThat(systemOutRule.getLog()).contains("4. Supprimer une pizza");
		assertThat(systemOutRule.getLog()).contains("99. sortir");

	}

}
