package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Znamenitost database table.
 * 
 */
@Entity
@NamedQuery(name="Znamenitost.findAll", query="SELECT z FROM Znamenitost z")
public class Znamenitost implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idZnamenitost;

	private String opis;

	private String tip;

	private boolean zakazujeSe;

	//bi-directional many-to-one association to Komentarise
	@OneToMany(mappedBy="znamenitost")
	private List<Komentarise> komentarises;

	//bi-directional many-to-one association to Obilazi
	@OneToMany(mappedBy="znamenitost")
	private List<Obilazi> obilazis;

	//bi-directional many-to-one association to Slika
	@OneToMany(mappedBy="znamenitost")
	private List<Slika> slikas;

	//bi-directional many-to-one association to Termin
	@OneToMany(mappedBy="znamenitost")
	private List<Termin> termins;

	//bi-directional many-to-one association to Staza
	@ManyToOne
	@JoinColumn(name="Staza_idStaza")
	private Staza staza;

	public Znamenitost() {
	}

	public int getIdZnamenitost() {
		return this.idZnamenitost;
	}

	public void setIdZnamenitost(int idZnamenitost) {
		this.idZnamenitost = idZnamenitost;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getTip() {
		return this.tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public boolean getZakazujeSe() {
		return this.zakazujeSe;
	}

	public void setZakazujeSe(boolean zakazujeSe) {
		this.zakazujeSe = zakazujeSe;
	}

	public List<Komentarise> getKomentarises() {
		return this.komentarises;
	}

	public void setKomentarises(List<Komentarise> komentarises) {
		this.komentarises = komentarises;
	}

	public Komentarise addKomentaris(Komentarise komentaris) {
		getKomentarises().add(komentaris);
		komentaris.setZnamenitost(this);

		return komentaris;
	}

	public Komentarise removeKomentaris(Komentarise komentaris) {
		getKomentarises().remove(komentaris);
		komentaris.setZnamenitost(null);

		return komentaris;
	}

	public List<Obilazi> getObilazis() {
		return this.obilazis;
	}

	public void setObilazis(List<Obilazi> obilazis) {
		this.obilazis = obilazis;
	}

	public Obilazi addObilazi(Obilazi obilazi) {
		getObilazis().add(obilazi);
		obilazi.setZnamenitost(this);

		return obilazi;
	}

	public Obilazi removeObilazi(Obilazi obilazi) {
		getObilazis().remove(obilazi);
		obilazi.setZnamenitost(null);

		return obilazi;
	}

	public List<Slika> getSlikas() {
		return this.slikas;
	}

	public void setSlikas(List<Slika> slikas) {
		this.slikas = slikas;
	}

	public Slika addSlika(Slika slika) {
		getSlikas().add(slika);
		slika.setZnamenitost(this);

		return slika;
	}

	public Slika removeSlika(Slika slika) {
		getSlikas().remove(slika);
		slika.setZnamenitost(null);

		return slika;
	}

	public List<Termin> getTermins() {
		return this.termins;
	}

	public void setTermins(List<Termin> termins) {
		this.termins = termins;
	}

	public Termin addTermin(Termin termin) {
		getTermins().add(termin);
		termin.setZnamenitost(this);

		return termin;
	}

	public Termin removeTermin(Termin termin) {
		getTermins().remove(termin);
		termin.setZnamenitost(null);

		return termin;
	}

	public Staza getStaza() {
		return this.staza;
	}

	public void setStaza(Staza staza) {
		this.staza = staza;
	}

}