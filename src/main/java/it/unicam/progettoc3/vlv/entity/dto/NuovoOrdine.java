package it.unicam.progettoc3.vlv.entity.dto;

/**
 * La classe NuovoOrdine ha la parte piu' grezza (getter e setter) delle funzionalita' della classe Ordine, le due permettono di istanziare
 * due oggetti quasi equivalenti per quanto riguarda gli attributi interessanti da mostrare sulla piattaforma.
 * Questa classe è stata aggiunta per permettere di restituire un oggetto ordine, ma appunto solo con codiceRitiro, descrizione, id cliente
 * e id punto di ritiro
 */
public class NuovoOrdine{

	
	private String codiceRitiro;
	
	private String descrizione;


	private Long idCliente;
	

	private Long idPuntoDiRitiro;


	public NuovoOrdine() {
		// TODO Auto-generated constructor stub
	}
	
	public String getCodiceRitiro() {
		return codiceRitiro;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public Long getIdPuntoDiRitiro() {
		return idPuntoDiRitiro;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public NuovoOrdine(String codiceRitiro, String descrizione, Long idCliente, Long idPuntoDiRitiro) {
		super();
		this.codiceRitiro = codiceRitiro;
		this.descrizione = descrizione;
		this.idCliente = idCliente;
		this.idPuntoDiRitiro = idPuntoDiRitiro;
	}
	
	
	
}
