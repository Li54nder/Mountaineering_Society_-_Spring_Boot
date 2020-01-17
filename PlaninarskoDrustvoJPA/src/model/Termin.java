package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Termin database table.
 * 
 */
@Entity
@NamedQuery(name="Termin.findAll", query="SELECT t FROM Termin t")
public class Termin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idTermin;

	private String kraj;

	private String pocetak;

	//bi-directional many-to-one association to Znamenitost
	@ManyToOne
	@JoinColumn(name="Znamenitost_idZnamenitost")
	private Znamenitost znamenitost;

	//bi-directional many-to-one association to Zakazuje
	@OneToMany(mappedBy="termin")
	private List<Zakazuje> zakazujes;

	public Termin() {
	}

	public int getIdTermin() {
		return this.idTermin;
	}

	public void setIdTermin(int idTermin) {
		this.idTermin = idTermin;
	}

	public String getKraj() {
		return this.kraj;
	}

	public void setKraj(String kraj) {
		this.kraj = kraj;
	}

	public String getPocetak() {
		return this.pocetak;
	}

	public void setPocetak(String pocetak) {
		this.pocetak = pocetak;
	}

	public Znamenitost getZnamenitost() {
		return this.znamenitost;
	}

	public void setZnamenitost(Znamenitost znamenitost) {
		this.znamenitost = znamenitost;
	}

	public List<Zakazuje> getZakazujes() {
		return this.zakazujes;
	}

	public void setZakazujes(List<Zakazuje> zakazujes) {
		this.zakazujes = zakazujes;
	}

	public Zakazuje addZakazuje(Zakazuje zakazuje) {
		getZakazujes().add(zakazuje);
		zakazuje.setTermin(this);

		return zakazuje;
	}

	public Zakazuje removeZakazuje(Zakazuje zakazuje) {
		getZakazujes().remove(zakazuje);
		zakazuje.setTermin(null);

		return zakazuje;
	}

}