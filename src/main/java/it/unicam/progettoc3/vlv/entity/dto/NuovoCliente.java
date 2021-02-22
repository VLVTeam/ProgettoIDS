package it.unicam.progettoc3.vlv.entity.dto;

import javax.validation.constraints.NotBlank;

/**
 * La classe NuovoCliente ha la parte piu' grezza (getter e setter) delle funzionalita' della classe Cliente, le due permettono di istanziare
 * due oggetti quasi equivalenti per quanto riguarda gli attributi interessanti da mostrare sulla piattaforma.
 * Questa classe è stata aggiunta per permettere di restituire e/o modificare un oggetto cliente, ma appunto solo con nome, cognome e indirizzo
 */
public class NuovoCliente extends NuovoUtente {

	@NotBlank
	private String nome;
	@NotBlank
	private String cognome;
	@NotBlank
	private String indirizzo;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	
	public NuovoCliente(){
		super();
	}
	
	
	public NuovoCliente( @NotBlank  String email,  @NotBlank String password,
			 @NotBlank String nome,  @NotBlank String cognome,  @NotBlank String indirizzo){
		super(email, password);
		this.email = email;
		this.password = password;
		this.cognome = cognome;
		this.nome = nome;
		this.indirizzo = indirizzo;	
	}
	
}