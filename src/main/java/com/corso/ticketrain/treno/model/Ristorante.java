package com.corso.ticketrain.treno.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ristorante")
public class Ristorante extends Vagone{
	
	@Column(name = "numero_coperti", nullable=false)
	private int numeroCoperti;
	@Column(name = "menu", nullable=false)
	private String menu;

	public Ristorante() { 
		super();
	}
	
	public Ristorante(double peso, double lunghezza, char carattere, int numeroCoperti, String menu) {
		super(peso, lunghezza, carattere);
		this.numeroCoperti = numeroCoperti;
		this.menu = menu;
	}

	public int getNumeroCoperti() {
		return numeroCoperti;
	}

	public void setNumeroCoperti(int numeroCoperti) {
		this.numeroCoperti = numeroCoperti;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}
	
	
}
