package it.unicam.progettoc3.vlv.entity.dto;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import it.unicam.progettoc3.vlv.entity.enumeratori.CategorieMerceologiche;

/**
 * La classe NuovoCommerciante ha la parte piu' grezza (getter e setter) delle funzionalita' della classe Commerciante, le due permettono di istanziare
 * due oggetti quasi equivalenti per quanto riguarda gli attributi interessanti da mostrare sulla piattaforma.
 * Questa classe è stata aggiunta per permettere di restituire e/o modificare un oggetto commerciante, ma appunto solo con categoria merceologica,
 * indirizzo punto vendita e nome punto vendita
 */
public class NuovoCommerciante extends NuovoUtente {

	
	@NotNull
	@Enumerated(EnumType.STRING)
	private CategorieMerceologiche categoriaMerceologica;
	
	@NotNull
	private String indirizzoPuntoVendita;
	
	@NotNull
	private String nomePuntoVendita;

	public CategorieMerceologiche getCategoriaMerceologica() {
		return categoriaMerceologica;
	}

	public void setCategoriaMerceologica(CategorieMerceologiche categoriaMerceologica) {
		this.categoriaMerceologica = categoriaMerceologica;
	}

	public String getIndirizzoPuntoVendita() {
		return indirizzoPuntoVendita;
	}

	public void setIndirizzoPuntoVendita(String indirizzoPuntoVendita) {
		this.indirizzoPuntoVendita = indirizzoPuntoVendita;
	}

	public String getNomePuntoVendita() {
		return nomePuntoVendita;
	}

	public void setNomePuntoVendita(String nomePuntoVendita) {
		this.nomePuntoVendita = nomePuntoVendita;
	}
	
	
	public NuovoCommerciante(){}

	public NuovoCommerciante(@NotNull @NotBlank @Email String email, @NotNull @NotBlank String password,
			@NotNull @NotBlank CategorieMerceologiche categoria, @NotNull @NotBlank String indirizzo, @NotNull @NotBlank String nome){
		super(email, password);
		this.email = email;
		this.password = password;
		this.categoriaMerceologica = categoria;
		this.indirizzoPuntoVendita = indirizzo;
		this.nomePuntoVendita = nome;
	}

}
