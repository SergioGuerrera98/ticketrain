package com.corso.ticketrain.treno.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)  
public abstract class Vagone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "peso", nullable=false)
	private double peso;
	@Column(name = "lunghezza", nullable=false)
	private double lunghezza;
	@Column(name = "carattere", nullable=false)
	private char carattere;

	public Vagone() { }
	
	public Vagone(double peso, double lunghezza, char carattere) {
		super();
		this.peso = peso;
		this.lunghezza = lunghezza;
		this.carattere = carattere;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getLunghezza() {
		return lunghezza;
	}

	public void setLunghezza(double lunghezza) {
		this.lunghezza = lunghezza;
	}

	public char getCarattere() {
		return carattere;
	}

	public void setCarattere(char carattere) {
		this.carattere = carattere;
	}	
}
