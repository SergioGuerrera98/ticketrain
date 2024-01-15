package com.corso.ticketrain.treno.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "locomotiva")
public class Locomotiva extends Vagone{
	
	@Column(name = "velocita", nullable=false)
	private double velocita;
	@Column(name = "pesoTrainabile", nullable=false)
	private double pesoTrainabile;
	
	public Locomotiva(){
		super();
	 }
	
	public Locomotiva(double peso, double lunghezza, char carattere, double velocita, double pesoTrainabile) {
		super(peso, lunghezza, carattere);
		this.velocita = velocita;
		this.pesoTrainabile = pesoTrainabile;
	}

	public double getVelocita() {
		return velocita;
	}

	public void setVelocita(double velocita) {
		this.velocita = velocita;
	}

	public double getPesoTrainabile() {
		return pesoTrainabile;
	}

	public void setPesoTrainabile(double pesoTrainabile) {
		this.pesoTrainabile = pesoTrainabile;
	}

	@Override
	public double getNumeroPosti() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setNumeroPosti(double d) {
		// TODO Auto-generated method stub
		
	}
	

}
