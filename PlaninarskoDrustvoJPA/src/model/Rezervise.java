package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Rezervise database table.
 * 
 */
@Entity
@NamedQuery(name="Rezervise.findAll", query="SELECT r FROM Rezervise r")
public class Rezervise implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRezervise;

	@Temporal(TemporalType.TIMESTAMP)
	private Date kraj;

	@Temporal(TemporalType.TIMESTAMP)
	private Date pocetak;

	//bi-directional many-to-one association to Izvestaj
	@OneToMany(mappedBy="rezervise")
	private List<Izvestaj> izvestajs;

	//bi-directional many-to-one association to Komentarise
	@OneToMany(mappedBy="rezervise")
	private List<Komentarise> komentarises;

	//bi-directional many-to-one association to Obilazi
	@OneToMany(mappedBy="rezervise")
	private List<Obilazi> obilazis;

	//bi-directional many-to-one association to Dom
	@ManyToOne
	@JoinColumn(name="Dom_idDom")
	private Dom dom;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="Korisnik_idKorisnik")
	private Korisnik korisnik;

	//bi-directional many-to-one association to Zakazuje
	@OneToMany(mappedBy="rezervise")
	private List<Zakazuje> zakazujes;

	public Rezervise() {
	}

	public int getIdRezervise() {
		return this.idRezervise;
	}

	public void setIdRezervise(int idRezervise) {
		this.idRezervise = idRezervise;
	}

	public Date getKraj() {
		return this.kraj;
	}

	public void setKraj(Date kraj) {
		this.kraj = kraj;
	}

	public Date getPocetak() {
		return this.pocetak;
	}

	public void setPocetak(Date pocetak) {
		this.pocetak = pocetak;
	}

	public List<Izvestaj> getIzvestajs() {
		return this.izvestajs;
	}

	public void setIzvestajs(List<Izvestaj> izvestajs) {
		this.izvestajs = izvestajs;
	}

	public Izvestaj addIzvestaj(Izvestaj izvestaj) {
		getIzvestajs().add(izvestaj);
		izvestaj.setRezervise(this);

		return izvestaj;
	}

	public Izvestaj removeIzvestaj(Izvestaj izvestaj) {
		getIzvestajs().remove(izvestaj);
		izvestaj.setRezervise(null);

		return izvestaj;
	}

	public List<Komentarise> getKomentarises() {
		return this.komentarises;
	}

	public void setKomentarises(List<Komentarise> komentarises) {
		this.komentarises = komentarises;
	}

	public Komentarise addKomentaris(Komentarise komentaris) {
		getKomentarises().add(komentaris);
		komentaris.setRezervise(this);

		return komentaris;
	}

	public Komentarise removeKomentaris(Komentarise komentaris) {
		getKomentarises().remove(komentaris);
		komentaris.setRezervise(null);

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
		obilazi.setRezervise(this);

		return obilazi;
	}

	public Obilazi removeObilazi(Obilazi obilazi) {
		getObilazis().remove(obilazi);
		obilazi.setRezervise(null);

		return obilazi;
	}

	public Dom getDom() {
		return this.dom;
	}

	public void setDom(Dom dom) {
		this.dom = dom;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public List<Zakazuje> getZakazujes() {
		return this.zakazujes;
	}

	public void setZakazujes(List<Zakazuje> zakazujes) {
		this.zakazujes = zakazujes;
	}

	public Zakazuje addZakazuje(Zakazuje zakazuje) {
		getZakazujes().add(zakazuje);
		zakazuje.setRezervise(this);

		return zakazuje;
	}

	public Zakazuje removeZakazuje(Zakazuje zakazuje) {
		getZakazujes().remove(zakazuje);
		zakazuje.setRezervise(null);

		return zakazuje;
	}

}