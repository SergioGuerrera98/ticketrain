package com.corso.ticketrain.treno.model;

import javax.persistence.*;

@Entity
@Table(name="passeggeri")public class Passeggeri extends Vagone {

	@Column(name = "numero_posti", nullable=false)
	private double numeroPosti;
	@Column(name = "classe", nullable=false)
	private int classe;


	public Passeggeri() { 
		super();
	}

	public Passeggeri(double peso, double lunghezza, char carattere, double numeroPosti, int classe) {
		super(peso, lunghezza, carattere);
		this.numeroPosti = numeroPosti;
		this.classe = classe;
	}

	public double getNumeroPosti() {
		return numeroPosti;
	}

	public void setNumeroPosti(double numeroPosti) {
		this.numeroPosti = numeroPosti;
	}

	public int getClasse() {
		return classe;
	}

	public void setClasse(int classe) {
		this.classe = classe;
	}

	
	

}
