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
	
	public Paese(){ }
	
	public Paese(String nomePaese){
		this.nomePaese=nomePaese;
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

}
