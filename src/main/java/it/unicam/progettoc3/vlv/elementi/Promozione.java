package it.unicam.progettoc3.vlv.elementi;



import java.util.Date;

import it.unicam.progettoc3.vlv.utenti.Commerciante;

public class Promozione {
private int ID;
private String descrizione;
private Commerciante commerciante;
private Date dataInizio;
private Date dataFine;




public Promozione(int ID, String descrizione, Commerciante commerciante, Date dataInizio, Date dataFine) {
	this.ID = ID;
	this.descrizione = descrizione;
	this.commerciante = commerciante;
	this.dataInizio = dataInizio;
	this.dataFine = dataFine;
}

public int getID() {
	return ID;
}

public void setID(int iD) {
	ID = iD;
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






}
