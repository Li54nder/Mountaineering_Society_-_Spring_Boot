package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Izvestaj database table.
 * 
 */
@Entity
@NamedQuery(name="Izvestaj.findAll", query="SELECT i FROM Izvestaj i")
public class Izvestaj implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idIzvestaj;

	private String sadrzaj;

	//bi-directional many-to-one association to Rezervise
	@ManyToOne
	@JoinColumn(name="Rezervise_idRezervise")
	private Rezervise rezervise;

	//bi-directional many-to-one association to Slika
	@OneToMany(mappedBy="izvestaj")
	private List<Slika> slikas;

	public Izvestaj() {
	}

	public int getIdIzvestaj() {
		return this.idIzvestaj;
	}

	public void setIdIzvestaj(int idIzvestaj) {
		this.idIzvestaj = idIzvestaj;
	}

	public String getSadrzaj() {
		return this.sadrzaj;
	}

	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}

	public Rezervise getRezervise() {
		return this.rezervise;
	}

	public void setRezervise(Rezervise rezervise) {
		this.rezervise = rezervise;
	}

	public List<Slika> getSlikas() {
		return this.slikas;
	}

	public void setSlikas(List<Slika> slikas) {
		this.slikas = slikas;
	}

	public Slika addSlika(Slika slika) {
		getSlikas().add(slika);
		slika.setIzvestaj(this);

		return slika;
	}

	public Slika removeSlika(Slika slika) {
		getSlikas().remove(slika);
		slika.setIzvestaj(null);

		return slika;
	}

}