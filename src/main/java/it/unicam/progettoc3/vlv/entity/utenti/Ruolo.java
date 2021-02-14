package it.unicam.progettoc3.vlv.entity.utenti;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import javax.persistence.OneToOne;


import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Ruolo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id ;
	
	/*
	@NotNull
	@Enumerated(EnumType.STRING)
 private NomiRuoli nomeRuolo;
*/
	
	/*
	@ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY  )
	@JsonBackReference
	private Utente utente;
	*/
	@JsonBackReference
	@OneToOne(cascade = CascadeType.ALL , optional = false , mappedBy= "associato" , fetch = FetchType.LAZY)
	Utente utente ;
	
	public Ruolo() {
		
	}
/*
	public Ruolo( @NotNull NomiRuoli nomeRuolo) {
		
		
		this.nomeRuolo = nomeRuolo;
		setStatoUtente(nomeRuolo);
		
		
	}
	*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
/*
	public NomiRuoli getNomeRuolo() {
		return nomeRuolo;
	}

	public void setNomeRuolo(NomiRuoli nomeRuolo) {
		this.nomeRuolo = nomeRuolo;
	}
	*/

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
		//setStatoUtente(nomeRuolo);
	}
	
	
	public Long getIdUtente(){
		if (utente!= null)
		return utente.getId();
		else return null;
	}
	/*
	public void setStatoUtente(NomiRuoli nomeRuolo)
	{
		if(this.utente!=null){
			if(nomeRuolo== NomiRuoli.ROLE_COMMERCIANTE || nomeRuolo == NomiRuoli.ROLE_CORRIERE) 
				this.utente.setStato(false);
			}
	}
	*/
	
}
