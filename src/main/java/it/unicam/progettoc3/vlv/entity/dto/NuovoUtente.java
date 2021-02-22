package it.unicam.progettoc3.vlv.entity.dto;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * La classe NuovoUtente ha la parte piu' grezza (getter e setter) delle funzionalita' della classe Utente, le due permettono di istanziare
 * due oggetti quasi equivalenti per quanto riguarda gli attributi interessanti da mostrare sulla piattaforma.
 * Questa classe è stata aggiunta per permettere di restituire e/o modificare un oggetto utente, ma appunto solo con email e password
 */
public class NuovoUtente {

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

	
	public NuovoUtente(){}
	
	
	public NuovoUtente(@NotNull @NotBlank @Email String email, @NotNull @NotBlank String password) {
		this.email = email;
		this.password = password;
	}
	
	
}