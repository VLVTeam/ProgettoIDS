package it.unicam.progettoc3.vlv.entity.utenti;


import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;


import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import it.unicam.progettoc3.vlv.entity.elementi.Ordine;
import it.unicam.progettoc3.vlv.entity.elementi.Promozione;
import it.unicam.progettoc3.vlv.entity.enumeratori.CategorieMerceologiche;


@Entity
/**
 * classe model relativa al commerciante, si limita ad estendere la classe astratta 'Ruolo' e a fornire 
 * degli attributi relativi all'utente 'Commerciante', come categoria merceologica, indirizzo e nome punto vendita, lista di ordini
 * e lista di promozioni collegate al commerciante. Tutto con i getter e i setter piu' importanti per il fine ultimo del progetto.
 */
public class Commerciante extends Ruolo  {
	

	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private CategorieMerceologiche categoriaMerceologica;
	
	@NotNull
	@Column(nullable = false)
	private String indirizzoPuntoVendita;
	
	@NotNull
	@Column(nullable = false)
	private String nomePuntoVendita;
	
	
	@JsonManagedReference(value="commerciante-ordine")
	@OneToMany(fetch = FetchType.LAZY ,  cascade = CascadeType.ALL , mappedBy="commerciante")
	List<Ordine> ordini ;
	
	
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY ,  cascade = CascadeType.ALL , mappedBy="commerciante" )
	List<Promozione> promozioni ;
	
	public Commerciante(){}
	public Commerciante(CategorieMerceologiche categoriaMerceologica, String indirizzoPuntoVendita,String nomePuntoVendita ) {
		
		this.categoriaMerceologica = categoriaMerceologica;
		this.indirizzoPuntoVendita = indirizzoPuntoVendita;
		this.nomePuntoVendita = nomePuntoVendita;
		
	}

	public CategorieMerceologiche getCategoriaMerceologica() {
		return categoriaMerceologica;
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
	
	
	public void setCategoriaMerceologica(CategorieMerceologiche categoriaMerceologica) {
		this.categoriaMerceologica = categoriaMerceologica;
	}
	public void setNomePuntoVendita(String nomePuntoVendita) {
		this.nomePuntoVendita = nomePuntoVendita;
	}
	
	public List<Ordine> getOrdini() {
		return ordini;
	}
	public void setOrdini(List<Ordine> ordini) {
		this.ordini = ordini;
	}
	public List<Promozione> getPromozioni() {
		return promozioni;
	}
	public void setPromozioni(List<Promozione> promozioni) {
		this.promozioni = promozioni;
	}
	
	
	
}