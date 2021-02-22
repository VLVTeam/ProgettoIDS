package it.unicam.progettoc3.vlv.entity.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * La classe NuovoCorriere ha la parte piu' grezza (getter e setter) delle funzionalita' della classe Corriere, le due permettono di istanziare
 * due oggetti quasi equivalenti per quanto riguarda gli attributi interessanti da mostrare sulla piattaforma.
 * Questa classe è stata aggiunta per permettere di restituire e/o modificare un oggetto corriere, ma appunto solo con nomeDitta
 */
public class NuovoCorriere extends NuovoUtente {

	
	@NotNull
	private String nomeDitta;

	public String getNomeDitta() {
		return nomeDitta;
	}

	public void setNomeDitta(String nomeDitta) {
		this.nomeDitta = nomeDitta;
	}
	
	public NuovoCorriere(){}
	
	public NuovoCorriere(@NotNull @NotBlank @Email String email, @NotNull @NotBlank String password, @NotNull @NotBlank String nomeDitta) {
		this.email = email;
		this.password = password;
		this.nomeDitta = nomeDitta;
	}
	
}