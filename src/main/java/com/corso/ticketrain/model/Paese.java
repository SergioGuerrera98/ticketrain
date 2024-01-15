package com.corso.ticketrain.model;

import javax.persistence.*;

@Entity
@Table(name = "paese")
public class Paese {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "nomePaese", nullable = false, length = 255)
	private String nomePaese;
	@Column(name = "alpha_2", length = 2)
	private String alpha2;
	
	public Paese(){ }
	
	public Paese(String nomePaese){
		this.nomePaese=nomePaese;
	}
	
	public Paese(String nomePaese, String alpha2){
		this.nomePaese=nomePaese;
		this.alpha2=alpha2;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getNomePaese(){
		return this.nomePaese;
	}
	
	public void setNomePaese(String nomePaese){
		this.nomePaese=nomePaese;
	}
	
	public String getAlpha2(){
		return this.alpha2;
	}
	
	public void setAlpha2(String alpha2){
		this.alpha2=alpha2;
	}

}
