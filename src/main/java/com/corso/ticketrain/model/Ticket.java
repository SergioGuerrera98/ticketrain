package com.corso.ticketrain.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.*;

import com.corso.ticketrain.treno.model.Treno;
import com.corso.ticketrain.treno.model.Vagone;

@Entity
@Table(name = "ticket")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "codice", unique = true)
	private String codice;
	@Column(name = "dataPartenza")
	private LocalDateTime dataPartenza;
	@Column(name = "dataArrivo")
	private LocalDateTime dataArrivo;
	@Column(name = "luogoPartenza")
	private String luogoPartenza;
	@Column(name = "luogoArrivo")
	private String luogoArrivo;
	@Column(name = "prezzo")
	private Float prezzo;
	@ManyToOne
	@JoinColumn(name = "treno_id") 
	private Treno treno_id;
	@ManyToOne
	@JoinColumn(name = "vagone_id") 
	private Vagone vagone_id;
	
	public Ticket() {
		super();
	}
	
	public Ticket(String codice, LocalDateTime dataPartenza,
					LocalDateTime dataArrivo, String luogoPartenza, String luogoArrivo, Float prezzo, Treno treno_id, Vagone vagone_id){
						this.codice = codice;
						this.dataPartenza = dataPartenza;
						this.dataArrivo = dataArrivo;
						this.luogoArrivo = luogoArrivo;
						this.luogoPartenza = luogoPartenza;
						this.prezzo = prezzo;
						this.treno_id = treno_id;
						this.vagone_id = vagone_id;
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

	public LocalDateTime getDataPartenza() {
		return dataPartenza;
	}

	public void setDataPartenza(LocalDateTime dataPartenza) {
		this.dataPartenza = dataPartenza;
	}

	public LocalDateTime getDataArrivo() {
		return dataArrivo;
	}

	public void setDataArrivo(LocalDateTime dataArrivo) {
		this.dataArrivo = dataArrivo;
	}

	public String getLuogoPartenza() {
		return luogoPartenza;
	}

	public void setLuogoPartenza(String luogoPartenza) {
		this.luogoPartenza = luogoPartenza;
	}

	public String getLuogoArrivo() {
		return luogoArrivo;
	}

	public void setLuogoArrivo(String luogoArrivo) {
		this.luogoArrivo = luogoArrivo;
	}

	public Treno getTreno_id() {
		return treno_id;
	}

	public void setTreno_id(Treno treno_id) {
		this.treno_id = treno_id;
	}

	public Vagone getVagone_id() {
		return vagone_id;
	}

	public void setVagone_id(Vagone vagone_id) {
		this.vagone_id = vagone_id;
	}

	public Float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Float prezzo) {
		this.prezzo = prezzo;
	}

	@Override
	public String toString() {
		return "Ticket [codice=" + codice + ", dataPartenza=" + dataPartenza + ", dataArrivo=" + dataArrivo
				+ ", luogoPartenza=" + luogoPartenza + ", luogoArrivo=" + luogoArrivo + ", prezzo=" + prezzo
				+ ", treno_id=" + treno_id + ", vagone_id=" + vagone_id + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(codice, dataArrivo, dataPartenza, luogoArrivo, luogoPartenza, prezzo, treno_id, vagone_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		return Objects.equals(codice, other.codice) && Objects.equals(dataArrivo, other.dataArrivo)
				&& Objects.equals(dataPartenza, other.dataPartenza) && Objects.equals(luogoArrivo, other.luogoArrivo)
				&& Objects.equals(luogoPartenza, other.luogoPartenza) && Objects.equals(prezzo, other.prezzo)
				&& Objects.equals(treno_id, other.treno_id) && Objects.equals(vagone_id, other.vagone_id);
	}
	
	
	
}
