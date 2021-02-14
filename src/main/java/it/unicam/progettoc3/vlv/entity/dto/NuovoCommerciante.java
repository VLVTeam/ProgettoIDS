package it.unicam.progettoc3.vlv.entity.dto;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import it.unicam.progettoc3.vlv.entity.enumeratori.CategorieMerceologiche;

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



}
