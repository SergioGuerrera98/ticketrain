package com.corso.ticketrain.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.*;

import com.corso.ticketrain.treno.model.Treno;
import com.corso.ticketrain.treno.model.Vagone;

//@Grassottello
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
	@Column(name = "classe")
	private String classe;
	@ManyToOne
	@JoinColumn(name = "treno_id") 
	private Treno treno_id;
	@ManyToOne
	@JoinColumn(name = "vagone_id") 
	private Vagone vagone_id;
	
	public Ticket() {
		super();
	}
	
	
	
	public Ticket(String codice, LocalDateTime dataPartenza, LocalDateTime dataArrivo, String luogoPartenza,
			String luogoArrivo, Float prezzo, Treno treno_id, String classe, Vagone vagone_id) {
		super();
		this.codice = codice;
		this.dataPartenza = dataPartenza;
		this.dataArrivo = dataArrivo;
		this.luogoPartenza = luogoPartenza;
		this.luogoArrivo = luogoArrivo;
		this.prezzo = prezzo;
		this.treno_id = treno_id;
		this.classe = classe;
		this.vagone_id = vagone_id;
	}

	public Ticket(String codice, LocalDateTime dataPartenza, LocalDateTime dataArrivo, String luogoPartenza,
			String luogoArrivo, Float prezzo, String classe, Treno treno_id, Vagone vagone_id) {
		super();
		this.codice = codice;
		this.dataPartenza = dataPartenza;
		this.dataArrivo = dataArrivo;
		this.luogoPartenza = luogoPartenza;
		this.luogoArrivo = luogoArrivo;
		this.prezzo = prezzo;
		this.classe = classe;
		this.treno_id = treno_id;
		this.vagone_id = vagone_id;
	}



	public int getId() {
		return id;
	}

	public Ticket setId(int id) {
		this.id = id;
		return this;
	}

	public String getCodice() {
		return codice;
	}

	public Ticket setCodice(String codice) {
		this.codice = codice;
		return this;
	}

	public LocalDateTime getDataPartenza() {
		return dataPartenza;
	}
	
	public String getDataPartenzaStr() {
		return getDataPartenza().getYear() + "/" +
			padTwo(getDataPartenza().getMonth().getValue()) + "/" +
			padTwo(getDataPartenza().getDayOfMonth()) + " - " +
			padTwo(getDataPartenza().getHour()) + ":" +
			padTwo(getDataPartenza().getMinute());
	}

	public Ticket setDataPartenza(LocalDateTime dataPartenza) {
		this.dataPartenza = dataPartenza;
		return this;
	}

	public LocalDateTime getDataArrivo() {
		return dataArrivo;
	}

	public String getDataArrivoStr() {
		return getDataArrivo().getYear() + "/" +
			padTwo(getDataArrivo().getMonth().getValue()) + "/" +
			padTwo(getDataArrivo().getDayOfMonth()) + " - " +
			padTwo(getDataArrivo().getHour()) + ":" +
			padTwo(getDataArrivo().getMinute());
	}

	private String padTwo(int i) {
		String str = i+"";
		if (str.length() < 2)
			str = "0" + str;

		return str;
	}

	public Ticket setDataArrivo(LocalDateTime dataArrivo) {
		this.dataArrivo = dataArrivo;
		return this;
	}

	public String getLuogoPartenza() {
		return luogoPartenza;
	}

	public Ticket setLuogoPartenza(String luogoPartenza) {
		this.luogoPartenza = luogoPartenza;
		return this;
	}

	public String getLuogoArrivo() {
		return luogoArrivo;
	}

	public Ticket setLuogoArrivo(String luogoArrivo) {
		this.luogoArrivo = luogoArrivo;
		return this;
	}

	public Treno getTreno_id() {
		return treno_id;
	}

	public Ticket setTreno_id(Treno treno_id) {
		this.treno_id = treno_id;
		return this;
	}

	public Vagone getVagone_id() {
		return vagone_id;
	}

	public Ticket setVagone_id(Vagone vagone_id) {
		this.vagone_id = vagone_id;
		return this;
	}

	public Float getPrezzo() {
		return prezzo;
	}
	public String getPrezzoCent() {
		String p = Float.toString(getPrezzo());
		return p.substring(p.indexOf(".")) + ".00";
	}

	public Ticket setPrezzo(Float prezzo) {
		this.prezzo = prezzo;
		return this;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
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
