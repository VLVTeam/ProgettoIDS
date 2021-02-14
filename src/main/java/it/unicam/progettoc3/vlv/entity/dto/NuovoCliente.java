package it.unicam.progettoc3.vlv.entity.dto;

import javax.validation.constraints.NotBlank;

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
	
	
	public NuovoCliente(){}
}
