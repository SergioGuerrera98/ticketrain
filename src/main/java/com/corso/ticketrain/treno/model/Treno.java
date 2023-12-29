package com.corso.ticketrain.treno.model;


import java.util.List;
import javax.persistence.*;


@Entity
@Table(name="treno")
public class Treno {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "codice", nullable=false, length=255)
	private String codice;
	@Column(name = "compagnia", nullable=false, length=255)
	private String compagnia;
	@OneToMany
	@JoinTable(name = "treno_vagone", 
		joinColumns = @JoinColumn(name = "treno_id"),
		inverseJoinColumns = @JoinColumn(name = "vagone_id"))
	private List<Vagone> vagoni;
	
	public Treno() {
		
	}
	
	public Treno(String codice, String compagnia, List<Vagone> vagoni) {
		this.codice = codice;
		this.compagnia = compagnia;
		this.vagoni = vagoni;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getCompagnia() {
		return compagnia;
	}

	public void setCompagnia(String compagnia) {
		this.compagnia = compagnia;
	}

	public List<Vagone> getVagoni() {
		return vagoni;
	}

	public void setVagoni(List<Vagone> vagoni) {
		this.vagoni = vagoni;
	}

	public StringBuilder getSiglaBuilder() {
		StringBuilder stringBuilder = new StringBuilder();
        for (Vagone vagone : vagoni) {
        	stringBuilder.append(vagone.getCarattere());
        }
        return stringBuilder;
	}
}