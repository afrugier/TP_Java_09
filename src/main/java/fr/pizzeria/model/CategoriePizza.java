package fr.pizzeria.model;

/**
 * @author pc
 * 
 * Génere les catégories des pizzas
 *
 */
public enum CategoriePizza {
	VIANDE("Viande"), POISSON("Poisson"), VEGETARIENNE("Végetarienne"), VEGETALIENNE("Végetalienne"), FROMAGES(
			"Fromages");

	public String libelle;

	/**
	 * @param libelle
	 */
	CategoriePizza(String libelle) {
		this.setLibelle(libelle);
	}

	/**
	 * @return
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
}
