package fr.pizzeria.model;

/**
 * @author pc
 *
 */
public class Pizza {

	int id;
	String code;
	String nom;
	double prix;
	static int compteur = 0;
	CategoriePizza categoriePizza;
	
	/**
	 * @return
	 */
	public CategoriePizza getCategoriePizza() {
		return categoriePizza;
	}
	/**
	 * @param categoriePizza
	 */
	public void setCategoriePizza(CategoriePizza categoriePizza) {
		this.categoriePizza = categoriePizza;
	}
	/**
	 * @param id
	 * @param code
	 * @param nom
	 * @param prix
	 */
	public Pizza(int id, String code, String nom, double prix, CategoriePizza categoriePizza) {
		this.id = id;
		this.nom = nom;
		this.code = code;
		this.prix = prix;
		this.categoriePizza = categoriePizza;
	}
	/**
	 * @param id
	 * @param code
	 * @param nom
	 * @param prix
	 */
	public Pizza(String code, String nom, double prix, CategoriePizza categoriePizza) {
		this.id = compteur++;
		this.nom = nom;
		this.code = code;
		this.prix = prix;
		this.categoriePizza = categoriePizza;
	}

	/**
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return
	 */
	public double getPrix() {
		return prix;
	}

	/**
	 * @param prix
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}

}
