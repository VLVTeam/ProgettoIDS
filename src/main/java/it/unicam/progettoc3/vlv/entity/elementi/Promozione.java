package it.unicam.progettoc3.vlv.entity.elementi;



import java.util.Date;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import it.unicam.progettoc3.vlv.entity.utenti.Commerciante;

@Entity
/**
 * classe model relativa alla promozione, si limita a fornire i getter e i setter piu' importanti
 * per il fine ultimo del progetto.
 */
public class Promozione {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)		
private Long ID;

@Column(nullable = false)
@NotNull
@NotBlank
private String descrizione;

@JsonBackReference
@ManyToOne( fetch = FetchType.LAZY, cascade = CascadeType.ALL , optional = false )
@NotNull
private Commerciante commerciante;

@Temporal(TemporalType.DATE)
@NotNull
@Column(nullable = false)
private Date dataInizio;

@Temporal(TemporalType.DATE)
@NotNull
@Column(nullable = false)
private Date dataFine;



public Promozione(){}
public Promozione( String descrizione, Commerciante commerciante, Date dataInizio, Date dataFine) {
	
	this.descrizione = descrizione;
	this.commerciante = commerciante;
	this.dataInizio = dataInizio;
	this.dataFine = dataFine;
}

public Long getID() {
	return ID;
}


public String getDescrizione() {
	return descrizione;
}


public Commerciante getCommerciante() {
	return commerciante;
}



public Date getDataInizio() {
	return dataInizio;
}



public Date getDataFine() {
	return dataFine;
}
public void setDescrizione(String descrizione) {
	this.descrizione = descrizione;
}
public void setDataInizio(Date dataInizio) {
	this.dataInizio = dataInizio;
}
public void setDataFine(Date dataFine) {
	this.dataFine = dataFine;
}

@JsonIgnore
public Long getIdCommerciante(){
	return commerciante.getId();
}

public void setID(Long iD) {
	ID = iD;
}

public void setCommerciante(Commerciante commerciante) {
	this.commerciante = commerciante;
}





}
