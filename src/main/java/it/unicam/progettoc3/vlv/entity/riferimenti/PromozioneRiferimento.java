package it.unicam.progettoc3.vlv.entity.riferimenti;

import java.util.Date;


public class PromozioneRiferimento {
	
	private Long id;

	
	private String descrizione;

	
	private Date dataInizio;

	
	private Date dataFine;
	
	private Long idCommerciante;
	
	public PromozioneRiferimento() {
		// TODO Auto-generated constructor stub
	}
	/*
	public PromozioneRiferimento(String descrizione, Long idCommerciante, Date dataInizio, Date dataFine) {
		// TODO Auto-generated constructor stub
		this.descrizione=descrizione;
		this.idCommerciante=idCommerciante;
		this.dataInizio=dataInizio;
		this.dataFine=dataFine;
	}
*/
	public PromozioneRiferimento(Long id ,String descrizione, Long idCommerciante, Date dataInizio, Date dataFine) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.descrizione=descrizione;
		this.idCommerciante=idCommerciante;
		this.dataInizio=dataInizio;
		this.dataFine=dataFine;
	}

	public Long getIdCommerciante() {
		return idCommerciante;
	}

	public Long getId() {
		return id;
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

	
}
