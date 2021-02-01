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
public class Amministratore {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long ID;

@Column(nullable = false)
@NotNull
@NotBlank
private String nome;

@Column(nullable = false )
@NotNull
@NotBlank
private String password;





@Column(nullable = false , unique=true )
@NotNull
@NotBlank
@Email
private String email;


@Column(nullable = false)
@NotNull
@NotBlank
private String cognome;

public Amministratore(){}
public Amministratore( String nome, String cognome , String email , String password) {
	
	this.nome = nome;
	this.cognome = cognome;
	this.email=email;
	this.password=password;
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

public String getPassword() {
	return password;
}



public String getEmail() {
	return email;
}
}
