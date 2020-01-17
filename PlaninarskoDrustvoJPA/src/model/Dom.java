package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Dom database table.
 * 
 */
@Entity
@NamedQuery(name="Dom.findAll", query="SELECT d FROM Dom d")
public class Dom implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idDom;

	private int kapacitet;

	private String naziv;

	//bi-directional many-to-one association to Planina
	@ManyToOne
	@JoinColumn(name="Planina_idPlanina")
	private Planina planina;

	//bi-directional many-to-one association to Rezervise
	@OneToMany(mappedBy="dom")
	private List<Rezervise> rezervises;

	public Dom() {
	}

	public int getIdDom() {
		return this.idDom;
	}

	public void setIdDom(int idDom) {
		this.idDom = idDom;
	}

	public int getKapacitet() {
		return this.kapacitet;
	}

	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Planina getPlanina() {
		return this.planina;
	}

	public void setPlanina(Planina planina) {
		this.planina = planina;
	}

	public List<Rezervise> getRezervises() {
		return this.rezervises;
	}

	public void setRezervises(List<Rezervise> rezervises) {
		this.rezervises = rezervises;
	}

	public Rezervise addRezervis(Rezervise rezervis) {
		getRezervises().add(rezervis);
		rezervis.setDom(this);

		return rezervis;
	}

	public Rezervise removeRezervis(Rezervise rezervis) {
		getRezervises().remove(rezervis);
		rezervis.setDom(null);

		return rezervis;
	}

}