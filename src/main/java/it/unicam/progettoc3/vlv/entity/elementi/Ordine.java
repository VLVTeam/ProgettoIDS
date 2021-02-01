package it.unicam.progettoc3.vlv.entity.elementi;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import it.unicam.progettoc3.vlv.entity.enumeratori.StatiOrdine;
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

@JsonBackReference
@ManyToOne(fetch = FetchType.LAZY ,  cascade = CascadeType.ALL , optional= true)
private PuntoDiRitiro puntoDiRitiro;

@Enumerated(EnumType.STRING)
private StatiOrdine stato;

@JsonBackReference
@ManyToOne(fetch = FetchType.EAGER ,  cascade = CascadeType.ALL , optional = false)
@NotNull
private Cliente cliente;

@Temporal(TemporalType.DATE)
@CreationTimestamp
@Column(nullable=false)
private Date dataCreazione;

@Temporal(TemporalType.DATE)
private Date dataRitiro;

@Temporal(TemporalType.DATE)
private Date dataConsegnaPrevista;

public Ordine(){}
public Ordine(String codiceRitiro, String descrizione, Commerciante commerciante ,Cliente cliente ) {
	
	this.codiceRitiro = codiceRitiro;
	this.descrizione = descrizione;
	this.commerciante = commerciante;
	this.cliente=cliente;
	this.corriere=null;
	this.stato= StatiOrdine.IN_ACCETTAZIONE;
	this.dataRitiro=null;
	this.dataConsegnaPrevista=null;
	this.puntoDiRitiro=null;
}

public Ordine(String codiceRitiro, String descrizione, Commerciante commerciante ,Cliente cliente ,PuntoDiRitiro puntoDiRitiro) {
	
	this.codiceRitiro = codiceRitiro;
	this.descrizione = descrizione;
	this.commerciante = commerciante;
	this.cliente=cliente;
	this.corriere=null;
	this.stato= StatiOrdine.IN_ACCETTAZIONE;
	this.dataRitiro=null;
	this.dataConsegnaPrevista=null;
	this.puntoDiRitiro=puntoDiRitiro;
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
public StatiOrdine getStato() {
	return stato;
}
public void setStato(StatiOrdine statoOrdine) {
	this.stato = statoOrdine;
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
public Date getDataRitiro() {
	return dataRitiro;
}
public void setDataRitiro(Date dataRitiro) {
	this.dataRitiro = dataRitiro;
}
public Date getDataConsegnaPrevista() {
	return dataConsegnaPrevista;
}
public void setDataConsegnaPrevista(Date dataConsegnaPrevista) {
	this.dataConsegnaPrevista = dataConsegnaPrevista;
}

public Date getDataCreazione() {
	return dataCreazione;
}
public PuntoDiRitiro getPuntoDiRitiro() {
	return puntoDiRitiro;
}




}
