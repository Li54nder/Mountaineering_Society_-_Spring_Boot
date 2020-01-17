package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Zakazuje database table.
 * 
 */
@Entity
@NamedQuery(name="Zakazuje.findAll", query="SELECT z FROM Zakazuje z")
public class Zakazuje implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idZakazuje;

	//bi-directional many-to-one association to Termin
	@ManyToOne
	@JoinColumn(name="Termin_idTermin")
	private Termin termin;

	//bi-directional many-to-one association to Rezervise
	@ManyToOne
	@JoinColumn(name="Rezervise_idRezervise")
	private Rezervise rezervise;

	public Zakazuje() {
	}

	public int getIdZakazuje() {
		return this.idZakazuje;
	}

	public void setIdZakazuje(int idZakazuje) {
		this.idZakazuje = idZakazuje;
	}

	public Termin getTermin() {
		return this.termin;
	}

	public void setTermin(Termin termin) {
		this.termin = termin;
	}

	public Rezervise getRezervise() {
		return this.rezervise;
	}

	public void setRezervise(Rezervise rezervise) {
		this.rezervise = rezervise;
	}

}