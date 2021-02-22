package it.unicam.progettoc3.vlv.entity.dto;

import java.util.Date;

/**
 * La classe NuovaPromozione ha la parte piu' grezza (getter e setter) delle funzionalita' della classe Promozione, le due permettono di istanziare
 * due oggetti quasi equivalenti per quanto riguarda gli attributi interessanti da mostrare sulla piattaforma.
 * Questa classe è stata aggiunta per permettere di restituire un oggetto promozione, ma appunto solo con descrizione, dataInizio e dataFine
 */
public class NuovaPromozione {

	private String descrizione;

	private Date dataInizio;

	private Date dataFine;
	
	public NuovaPromozione() {
		// TODO Auto-generated constructor stub
	}

	public String getDescrizione() {
		return descrizione;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}
	
	
	public NuovaPromozione(String descrizione, Date dataInizio, Date dataFine) {
		super();
		this.descrizione = descrizione;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
	}

	
}
