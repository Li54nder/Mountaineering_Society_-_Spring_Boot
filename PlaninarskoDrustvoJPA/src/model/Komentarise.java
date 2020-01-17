package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Komentarise database table.
 * 
 */
@Entity
@NamedQuery(name="Komentarise.findAll", query="SELECT k FROM Komentarise k")
public class Komentarise implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKomentarise;

	private String komentar;

	//bi-directional many-to-one association to Rezervise
	@ManyToOne
	@JoinColumn(name="Rezervise_idRezervise")
	private Rezervise rezervise;

	//bi-directional many-to-one association to Znamenitost
	@ManyToOne
	@JoinColumn(name="Znamenitost_idZnamenitost")
	private Znamenitost znamenitost;

	public Komentarise() {
	}

	public int getIdKomentarise() {
		return this.idKomentarise;
	}

	public void setIdKomentarise(int idKomentarise) {
		this.idKomentarise = idKomentarise;
	}

	public String getKomentar() {
		return this.komentar;
	}

	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}

	public Rezervise getRezervise() {
		return this.rezervise;
	}

	public void setRezervise(Rezervise rezervise) {
		this.rezervise = rezervise;
	}

	public Znamenitost getZnamenitost() {
		return this.znamenitost;
	}

	public void setZnamenitost(Znamenitost znamenitost) {
		this.znamenitost = znamenitost;
	}

}