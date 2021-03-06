package it.unicam.progettoc3.vlv.entity.utenti;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import it.unicam.progettoc3.vlv.entity.enumeratori.NomiRuoli;





@Entity
/** 
 * la classe model Utente, e' la classe che inizializza qualsiasi utente, appunto, assegnandogli id, email e password; inoltre viene definito
 * anche un ruolo, che sara' utile poi per creare una classe dedicata proprio ad ogni tipo di utente, a seconda di questo nomeRuolo.
 * L'email e la password servono per l'autenticazione di ciascun utente
 */
public class Utente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(unique = true)
	private String email;
	
	@NotNull
	private String password;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private NomiRuoli nomeRuolo;
	
	private boolean stato;
	
	
	@JsonIgnore
	@NotNull
	@JsonManagedReference
	@OneToOne(cascade= CascadeType.ALL , fetch = FetchType.EAGER )
	Ruolo associato;

	public Utente() {
		
	}

	public Utente(@NotNull  String email, @NotNull String password , NomiRuoli nomeRuolo) {
		super();
		this.email = email;
		this.password = password;
		this.nomeRuolo=nomeRuolo;
		if(nomeRuolo==NomiRuoli.ROLE_COMMERCIANTE || nomeRuolo==NomiRuoli.ROLE_CORRIERE)
			this.stato=false;
		else this.stato=true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JsonIgnore
	public Ruolo getAssociato() {
		
		return associato;
	}

	public void setAssociato(Ruolo associato) {
		this.associato = associato;
	}
	
	
	@JsonIgnore
	public Long getIdAssociato(){
		if(associato==null) return null;
		return associato.getId();
	}
	
	

	public boolean isStato() {
		return stato;
	}

	public void setStato(boolean stato) {
		this.stato = stato;
	}
	@JsonIgnore
	public NomiRuoli getNomeRuolo() {
		return nomeRuolo;
	}

	public void setNomeRuolo(NomiRuoli nomeRuolo) {
		this.nomeRuolo = nomeRuolo;
	}
	
	
}