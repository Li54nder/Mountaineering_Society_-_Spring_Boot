package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Korisnik database table.
 * 
 */
@Entity
@NamedQuery(name="Korisnik.findAll", query="SELECT k FROM Korisnik k")
public class Korisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKorisnik;

	private String ime;

	private String korisnickoIme;

	private String prezime;

	private String sifra;

	//bi-directional many-to-one association to Clanarina
	@ManyToOne
	@JoinColumn(name="Clanarina_idClanarina")
	private Clanarina clanarina;

	//bi-directional many-to-one association to Uloga
	@ManyToOne
	@JoinColumn(name="Uloga_idUloga")
	private Uloga uloga;

	//bi-directional many-to-one association to Rezervise
	@OneToMany(mappedBy="korisnik")
	private List<Rezervise> rezervises;

	public Korisnik() {
	}

	public int getIdKorisnik() {
		return this.idKorisnik;
	}

	public void setIdKorisnik(int idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getKorisnickoIme() {
		return this.korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getSifra() {
		return this.sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public Clanarina getClanarina() {
		return this.clanarina;
	}

	public void setClanarina(Clanarina clanarina) {
		this.clanarina = clanarina;
	}

	public Uloga getUloga() {
		return this.uloga;
	}

	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}

	public List<Rezervise> getRezervises() {
		return this.rezervises;
	}

	public void setRezervises(List<Rezervise> rezervises) {
		this.rezervises = rezervises;
	}

	public Rezervise addRezervis(Rezervise rezervis) {
		getRezervises().add(rezervis);
		rezervis.setKorisnik(this);

		return rezervis;
	}

	public Rezervise removeRezervis(Rezervise rezervis) {
		getRezervises().remove(rezervis);
		rezervis.setKorisnik(null);

		return rezervis;
	}

}