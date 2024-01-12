package com.corso.ticketrain.model;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialBlob;

import java.sql.Blob;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="username", nullable=false, unique = true, length=255)
	private String username;
	@Column(name="password", nullable=false, length=255)
	private String password;
	@Column(name="admin", nullable=false)
	private boolean amministratore;
	@ManyToOne
	@JoinColumn(name = "paese_id", nullable=false)
	private Paese paese;
	
	
	@Lob
    @Column(name = "Foto")
    private byte[] photo;
	

	
	public User() {	}
	
	public User(int id, String username, String password, boolean amministratore, Paese paese) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.amministratore = amministratore;
		this.paese = paese;
	}
	
	public User(String username, String password, boolean amministratore, Paese paese) {
		this.username = username;
		this.password = password;
		this.amministratore = amministratore;
		this.paese = paese;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isAmministratore() {
		return this.amministratore;
	}
	
	public void setAmministratore(boolean amministratore) {
		this.amministratore = amministratore;
	}
	
	public Paese getPaese() {
		return this.paese;
	}
	
	public void setPaese(Paese paese) {
		this.paese = paese;
	}
	
	
	
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	

	public String toString() {
		return "User[" +
				"id: '" + id + "'" +
				", username: '" + username + "'" +
				",amministratore: '" + amministratore + "'" +
				", Paese: '" + paese + "'" +
				"]";
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) return true;
		if (object == null || getClass() != object.getClass()) return false;
		User user = (User) object;
		return Objects.equals(username, user.username);
	}

	@Override
	public int hashCode() {
		return Objects.hash(username);
	}
}
