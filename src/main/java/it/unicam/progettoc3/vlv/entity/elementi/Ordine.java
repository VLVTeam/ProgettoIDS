package it.unicam.progettoc3.vlv.entity.elementi;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import it.unicam.progettoc3.vlv.entity.enumeratori.StatoOrdine;
import it.unicam.progettoc3.vlv.entity.utenti.Cliente;
import it.unicam.progettoc3.vlv.entity.utenti.Commerciante;
import it.unicam.progettoc3.vlv.entity.utenti.Corriere;

@Entity
public class Ordine {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)	
private Long ID;

@Column(nullable = false)
@NotNull
@NotBlank
private String codiceRitiro;

private String descrizione;

@JsonBackReference
@ManyToOne(fetch = FetchType.EAGER ,  cascade = CascadeType.ALL , optional = false)
@NotNull
private Commerciante commerciante;

@JsonBackReference
@ManyToOne(fetch = FetchType.LAZY ,  cascade = CascadeType.ALL , optional= true)
private Corriere corriere;

@NotBlank
@NotNull
@Column(nullable = false)
@Enumerated(EnumType.STRING)
private StatoOrdine stato;

@JsonBackReference
@ManyToOne(fetch = FetchType.EAGER ,  cascade = CascadeType.ALL , optional = false)
@NotNull
private Cliente cliente;

public Ordine(){}
public Ordine(String codiceRitiro, String descrizione, Commerciante commerciante ,Cliente cliente) {
	
	this.codiceRitiro = codiceRitiro;
	this.descrizione = descrizione;
	this.commerciante = commerciante;
	this.cliente=cliente;
	this.corriere=null;
	this.stato=StatoOrdine.IN_ACCETTAZIONE;
}


public Cliente getCliente() {
	return cliente;
}


public Corriere getCorriere() {
	return corriere;
}
public void setCorriere(Corriere corriere) {
	this.corriere = corriere;
}
public StatoOrdine getStato() {
	return stato;
}
public void setStato(StatoOrdine stato) {
	this.stato = stato;
}
public Long getID() {
	return ID;
}
public String getCodiceRitiro() {
	return codiceRitiro;
}
public String getDescrizione() {
	return descrizione;
}
public Commerciante getCommerciante() {
	return commerciante;
}





}
