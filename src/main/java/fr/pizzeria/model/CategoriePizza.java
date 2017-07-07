package fr.pizzeria.model;

public enum CategoriePizza {
	VIANDE("Viande"), POISSON("Poisson"), VEGETARIENNE("Végetarienne"), VEGETALIENNE("Végetalienne"), FROMAGES(
			"Fromages");

	public String libelle;

	CategoriePizza(String libelle) {
		this.setLibelle(libelle);
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
}
