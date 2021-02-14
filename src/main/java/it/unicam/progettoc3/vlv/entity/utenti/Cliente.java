package it.unicam.progettoc3.vlv.entity.utenti;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.FetchType;


import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import it.unicam.progettoc3.vlv.entity.elementi.Ordine;

@Entity
public class Cliente extends Ruolo {
	
	
	
	@Column(nullable = false)
	@NotNull
	private String nome;
	
	@Column(nullable = false)
	@NotNull
	private String cognome;
	
	@Column(nullable = false)
	@NotNull
	private String indirizzo;
	
	

	@JsonManagedReference(value="cliente-ordine")
	@OneToMany(fetch = FetchType.LAZY ,  cascade = CascadeType.ALL , mappedBy="cliente")
	List<Ordine> ordini ;

	
	
	
	
	public Cliente(){}
	public Cliente(@NotNull String nome, @NotNull String cognome,@NotNull String indirizzo ) {
		
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		
		
	}
	
	
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
	
	
	public List<Ordine> getOrdini() {
		return ordini;
	}
	public void setOrdini(List<Ordine> ordini) {
		this.ordini = ordini;
	}
	
	
	
	
}
