package it.unicam.progettoc3.vlv.elementi;

import it.unicam.progettoc3.vlv.enumeratori.StatoOrdine;
import it.unicam.progettoc3.vlv.utenti.Cliente;
import it.unicam.progettoc3.vlv.utenti.Commerciante;
import it.unicam.progettoc3.vlv.utenti.Corriere;

public class Ordine {
private int ID;
private String codiceRitiro;
private String descrizione;
private Commerciante commerciante;
private Corriere corriere;
private StatoOrdine stato;
private Cliente cliente;


public Ordine(int ID, String codiceRitiro, String descrizione, Commerciante commerciante ,Cliente cliente) {
	this.ID = ID;
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
public int getID() {
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
