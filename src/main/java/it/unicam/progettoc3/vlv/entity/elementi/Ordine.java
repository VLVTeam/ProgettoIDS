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
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

import it.unicam.progettoc3.vlv.entity.enumeratori.StatiOrdine;
import it.unicam.progettoc3.vlv.entity.utenti.Cliente;
import it.unicam.progettoc3.vlv.entity.utenti.Commerciante;
import it.unicam.progettoc3.vlv.entity.utenti.Corriere;

@Entity
/**
 * classe model relativa all'ordine, si limita a fornire i getter e i setter piu' importanti
 * per il fine ultimo del progetto.
 */
public class Ordine {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)	
private Long ID;


private String codiceRitiro;

@Column(nullable = false)
@NotNull
@NotBlank
private String descrizione;

@JsonBackReference(value="commerciante-ordine")
@ManyToOne(fetch = FetchType.EAGER ,  cascade = CascadeType.ALL , optional = false)
@NotNull
private Commerciante commerciante;

@JsonBackReference(value="corriere-ordine")
@ManyToOne(fetch = FetchType.LAZY ,  cascade = CascadeType.ALL , optional= true)
private Corriere corriere;

@JsonBackReference(value="punto-ordine")
@OnDelete(action = OnDeleteAction.CASCADE)
@ManyToOne(fetch = FetchType.LAZY ,  cascade = CascadeType.ALL , optional= true )
private PuntoDiRitiro puntoDiRitiro;

@Enumerated(EnumType.STRING)
private StatiOrdine stato;

@JsonBackReference(value="cliente-ordine")
@ManyToOne(fetch = FetchType.EAGER ,   optional = false)
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
public Ordine( String descrizione, Commerciante commerciante ,Cliente cliente ) {
	
	this.codiceRitiro = null;
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
	if(puntoDiRitiro!=null)
	return codiceRitiro;
	else return null;
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


	public Long getIdCliente(){
		return cliente.getId();
	}

	public Long getIdCommerciante(){
		return commerciante.getId();
	}
	
	public Long getIdPuntoDiRitiro(){
		if (puntoDiRitiro != null)
		return puntoDiRitiro.getID();
		else return null;
	}
	
	public Long getIdCorriere(){
		if(corriere == null) return null;
		else
		return corriere.getId();
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public void setCodiceRitiro(String codiceRitiro) {
		this.codiceRitiro = codiceRitiro;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public void setCommerciante(Commerciante commerciante) {
		this.commerciante = commerciante;
	}
	public void setPuntoDiRitiro(PuntoDiRitiro puntoDiRitiro) {
		this.puntoDiRitiro = puntoDiRitiro;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	
}
