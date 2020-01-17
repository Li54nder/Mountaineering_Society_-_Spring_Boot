package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Obilazi database table.
 * 
 */
@Entity
@NamedQuery(name="Obilazi.findAll", query="SELECT o FROM Obilazi o")
public class Obilazi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idObilazi;

	//bi-directional many-to-one association to Rezervise
	@ManyToOne
	@JoinColumn(name="Rezervise_idRezervise")
	private Rezervise rezervise;

	//bi-directional many-to-one association to Znamenitost
	@ManyToOne
	@JoinColumn(name="Znamenitost_idZnamenitost")
	private Znamenitost znamenitost;

	public Obilazi() {
	}

	public int getIdObilazi() {
		return this.idObilazi;
	}

	public void setIdObilazi(int idObilazi) {
		this.idObilazi = idObilazi;
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