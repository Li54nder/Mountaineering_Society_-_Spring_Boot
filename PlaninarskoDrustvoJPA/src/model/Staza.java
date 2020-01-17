package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Staza database table.
 * 
 */
@Entity
@NamedQuery(name="Staza.findAll", query="SELECT s FROM Staza s")
public class Staza implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idStaza;

	@Lob
	private byte[] mapa;

	private String opis;

	private int tezina;

	//bi-directional many-to-one association to Planina
	@ManyToOne
	@JoinColumn(name="Planina_idPlanina")
	private Planina planina;

	//bi-directional many-to-one association to Znamenitost
	@OneToMany(mappedBy="staza")
	private List<Znamenitost> znamenitosts;

	public Staza() {
	}

	public int getIdStaza() {
		return this.idStaza;
	}

	public void setIdStaza(int idStaza) {
		this.idStaza = idStaza;
	}

	public byte[] getMapa() {
		return this.mapa;
	}

	public void setMapa(byte[] mapa) {
		this.mapa = mapa;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getTezina() {
		return this.tezina;
	}

	public void setTezina(int tezina) {
		this.tezina = tezina;
	}

	public Planina getPlanina() {
		return this.planina;
	}

	public void setPlanina(Planina planina) {
		this.planina = planina;
	}

	public List<Znamenitost> getZnamenitosts() {
		return this.znamenitosts;
	}

	public void setZnamenitosts(List<Znamenitost> znamenitosts) {
		this.znamenitosts = znamenitosts;
	}

	public Znamenitost addZnamenitost(Znamenitost znamenitost) {
		getZnamenitosts().add(znamenitost);
		znamenitost.setStaza(this);

		return znamenitost;
	}

	public Znamenitost removeZnamenitost(Znamenitost znamenitost) {
		getZnamenitosts().remove(znamenitost);
		znamenitost.setStaza(null);

		return znamenitost;
	}

}