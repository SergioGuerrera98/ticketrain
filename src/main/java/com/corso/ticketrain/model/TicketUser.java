package com.corso.ticketrain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ticket_user")
public class TicketUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "ticket_id")
	private Ticket ticket;

	@Column(name = "nome")
	private String nome;

	@Column(name = "cognome")
	private String cognome;

	@Column(name = "classe")
	private String classe;

	public TicketUser(User user, Ticket ticket, String nome, String cognome, String classe) {
		super();
		this.user = user;
		this.ticket = ticket;
		this.nome = nome;
		this.cognome = cognome;
		this.classe = classe;
	}
	
	public TicketUser() {}

	public int getId() {
		return id;
	}

	public TicketUser setId(int id) {
		this.id = id;
		return this;
	}

	public User getUser() {
		return user;
	}

	public TicketUser setUser(User user) {
		this.user = user;
		return this;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public TicketUser setTicket(Ticket ticket) {
		this.ticket = ticket;
		return this;
	}

	public String getNome() {
		return nome;
	}

	public TicketUser setNome(String nome) {
		this.nome = nome;
		return this;
	}

	public String getCognome() {
		return cognome;
	}

	public TicketUser setCognome(String cognome) {
		this.cognome = cognome;
		return this;
	}

	public String getClasse() {
		return classe;
	}

	public TicketUser setClasse(String classe) {
		this.classe = classe;
		return this;
	}

	@Override
	public String toString() {
		return "Biglietto utente=" + user.getUsername() + ", codice=" + ticket.getCodice() + ", nome=" + nome + ", cognome=" + cognome;
	}
	
	
	

}
