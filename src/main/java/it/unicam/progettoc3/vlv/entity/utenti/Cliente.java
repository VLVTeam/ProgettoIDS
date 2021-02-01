package it.unicam.progettoc3.vlv.entity.utenti;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;



@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;
	
	@Column(nullable = false)
	@NotNull
	@NotBlank
	private String nome;
	
	@Column(nullable = false)
	@NotNull
	@NotBlank
	private String cognome;
	
	@Column(nullable = false)
	@NotNull
	@NotBlank
	private String indirizzo;
	
	@Column(nullable = false )
	@NotNull
	@NotBlank
	private String password;





	@Column(nullable = false , unique=true )
	@NotNull
	@NotBlank
	@Email
	private String email;
	
	public Cliente(){}
	public Cliente(String nome, String cognome, String indirizzo , String email , String password) {
		
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.email=email;
		this.password=password;
	}
	
 public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
public Long getID() {
		return ID;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

 

}
