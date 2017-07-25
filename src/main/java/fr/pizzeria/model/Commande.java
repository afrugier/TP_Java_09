package fr.pizzeria.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Commande")
public class Commande {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "numero_commande")
	private Integer numeroCommande;

	private String statut;

	@Column(name = "date_commande")
	private LocalDate dateCommande;

	@ManyToOne
	@JoinColumn(name = "livreur_id")
	private Livreur livreurId;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client clientId;

	@ManyToMany
	@JoinTable(name = "Commande_Pizza", joinColumns = @JoinColumn(name = "commande_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "pizza_id", referencedColumnName = "id"))
	private List<Pizza> pizza;

	public Commande() {
		super();
	}

	public Commande(Integer id, Integer numeroCommande, String statut, LocalDate dateCommande, Livreur livreurId,
			Client clientId) {
		super();
		this.id = id;
		this.numeroCommande = numeroCommande;
		this.statut = statut;
		this.dateCommande = dateCommande;
		this.livreurId = livreurId;
		this.clientId = clientId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumeroCommande() {
		return numeroCommande;
	}

	public void setNumeroCommande(Integer numeroCommande) {
		this.numeroCommande = numeroCommande;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public LocalDate getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(LocalDate dateCommande) {
		this.dateCommande = dateCommande;
	}

	public Livreur getLivreurId() {
		return livreurId;
	}

	public void setLivreurId(Livreur livreurId) {
		this.livreurId = livreurId;
	}

	public Client getClientId() {
		return clientId;
	}

	public void setClientId(Client clientId) {
		this.clientId = clientId;
	}

}
