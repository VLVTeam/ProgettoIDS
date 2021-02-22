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
/**
 * classe model relativa al corriere, si limita ad estendere la classe astratta 'Ruolo' e a fornire 
 * degli attributi relativi all'utente 'Corriere', come nome ditta e lista di ordini presi in carico dal corriere, 
 * con i getter e i setter piu' importanti per il fine ultimo del progetto.
 */
public class Corriere extends Ruolo  {



	@Column(nullable = false)
	@NotNull
	private String nomeDitta;
	
	@JsonManagedReference(value="corriere-ordine")
	@OneToMany(fetch = FetchType.LAZY ,  cascade = CascadeType.ALL , mappedBy="corriere")
	List<Ordine> ordini ;
	
	public Corriere(){	}
	
	public Corriere( @NotNull String nomeDitta ) {
		
		this.nomeDitta = nomeDitta;
		
	
	}
	
	public String getNomeDitta() {
		return nomeDitta;
	}
	
	public void setNomeDitta(String nomeDitta) {
		this.nomeDitta = nomeDitta;
	}
	
	public List<Ordine> getOrdini() {
		return ordini;
	}
	
	public void setOrdini(List<Ordine> ordini) {
		this.ordini = ordini;
	}

	public Long getId() {
		return id;
	}

}
