package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Planina database table.
 * 
 */
@Entity
@NamedQuery(name="Planina.findAll", query="SELECT p FROM Planina p")
public class Planina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPlanina;

	private String naziv;

	//bi-directional many-to-one association to Dom
	@OneToMany(mappedBy="planina")
	private List<Dom> doms;

	//bi-directional many-to-one association to Staza
	@OneToMany(mappedBy="planina")
	private List<Staza> stazas;

	public Planina() {
	}

	public int getIdPlanina() {
		return this.idPlanina;
	}

	public void setIdPlanina(int idPlanina) {
		this.idPlanina = idPlanina;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Dom> getDoms() {
		return this.doms;
	}

	public void setDoms(List<Dom> doms) {
		this.doms = doms;
	}

	public Dom addDom(Dom dom) {
		getDoms().add(dom);
		dom.setPlanina(this);

		return dom;
	}

	public Dom removeDom(Dom dom) {
		getDoms().remove(dom);
		dom.setPlanina(null);

		return dom;
	}

	public List<Staza> getStazas() {
		return this.stazas;
	}

	public void setStazas(List<Staza> stazas) {
		this.stazas = stazas;
	}

	public Staza addStaza(Staza staza) {
		getStazas().add(staza);
		staza.setPlanina(this);

		return staza;
	}

	public Staza removeStaza(Staza staza) {
		getStazas().remove(staza);
		staza.setPlanina(null);

		return staza;
	}

}