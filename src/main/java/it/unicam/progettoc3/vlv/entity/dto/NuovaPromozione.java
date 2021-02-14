package it.unicam.progettoc3.vlv.entity.dto;

import java.util.Date;


public class NuovaPromozione {
	
	//private Long id;

	
	private String descrizione;

	
	private Date dataInizio;

	
	private Date dataFine;
	
	
	
	public NuovaPromozione() {
		// TODO Auto-generated constructor stub
	}
	/*
	public PromozioneRiferimento(String descrizione, Date dataInizio, Date dataFine) {
		// TODO Auto-generated constructor stub
		this.descrizione=descrizione;
		
		this.dataInizio=dataInizio;
		this.dataFine=dataFine;
	}
*/
	/*
	public PromozioneRiferimento(Long id ,String descrizione, Date dataInizio, Date dataFine) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.descrizione=descrizione;
		
		this.dataInizio=dataInizio;
		this.dataFine=dataFine;
	}

	*/
/*
	public Long getId() {
		return id;
	}
	*/

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
