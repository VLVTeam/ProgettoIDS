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
/**
 * classe astratta dedicata al ruolo, ed estesa da tutte le classi relative ai possibili utenti: Amministratore, Cliente, Commerciante e Corriere.
 * Questa classe astratta serve a collegare il ruolo definito nell'utente, alla classe dedicata proprio al ruolo definito, che corrisponde ad uno dei
 * possibili utenti appena citati, fornisce inoltre l'id.
 * 
 * Attraverso l'uso di questa interfaccia, viene sviluppato il design pattern 'Bridge'. Infatti questa astrazione permette di separare 
 * il set di classi strettamente correlate relative a tutti gli utenti in 2 gerarchie separate: astrazione('Ruolo') e 
 * implementazione ('Amministratore', 'Cliente', 'Commerciante', 'Corriere')
 */
public abstract class Ruolo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id ;
	
	@JsonBackReference
	@OneToOne(cascade = CascadeType.ALL , optional = false , mappedBy= "associato" , fetch = FetchType.LAZY)
	Utente utente ;
	
	public Ruolo() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	
	
	public Long getIdUtente(){
		if (utente!= null)
		return utente.getId();
		else return null;
	}
	
}
