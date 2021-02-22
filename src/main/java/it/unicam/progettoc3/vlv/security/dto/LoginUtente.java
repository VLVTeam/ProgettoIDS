package it.unicam.progettoc3.vlv.security.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Questa classe definisce un oggetto che deve venire trasferito sulla rete e rappresenta un utente che deve essere autenticato
 * attraverso email e password
 */
public class LoginUtente {

	
	@Email
	public String email;
	@NotBlank
	public String password;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	LoginUtente() {}
	
	public LoginUtente(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
}