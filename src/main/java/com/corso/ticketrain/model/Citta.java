package com.corso.ticketrain.model;

import javax.persistence.*;

@Entity
@Table(name = "citta")
public class Citta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nomeCitta", nullable = false, length = 255)
	private String nomeCitta;
	
	@ManyToOne
	@JoinColumn(name = "paese_id") 
	private Paese paese_Id;
	
	public Citta(){ }
	
	public Citta(String nomeCitta, Paese paese_Id){
		this.nomeCitta=nomeCitta;
		this.paese_Id=paese_Id;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getNomeCitta(){
		return this.nomeCitta;
	}
	
	public void setNomeCitta(String nomeCitta){
		this.nomeCitta=nomeCitta;
	}
	
	public Paese getPaese_Id(){
		return this.paese_Id;
	}
	
	public void setPaese_Id(Paese paese_Id){
		this.paese_Id=paese_Id;
	}
	
}