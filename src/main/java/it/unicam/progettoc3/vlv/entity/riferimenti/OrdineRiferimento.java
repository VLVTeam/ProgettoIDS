package it.unicam.progettoc3.vlv.entity.riferimenti;

import java.util.Date;



import it.unicam.progettoc3.vlv.entity.enumeratori.StatiOrdine;


public class OrdineRiferimento{

	



	private Long ID;

	
	private String codiceRitiro;

	private String descrizione;


	private StatiOrdine stato;

	private Long idCommerciante;

	private Long idCliente;
	
	private Long idCorriere;

	
	private Long idPuntoDiRitiro;

	
	
	

	
	private Date dataCreazione;

	
	private Date dataRitiro;

	
	private Date dataConsegnaPrevista;
	
	
	
	
	
	public OrdineRiferimento() {
		// TODO Auto-generated constructor stub
	}
/*
	public OrdineRiferimento(String codiceRitiro, String descrizione,StatiOrdine stato, Long idCommerciante, Long idCliente,  Long idPuntoDiRitiro ) {
		// TODO Auto-generated constructor stub
		
		this.codiceRitiro=codiceRitiro;
		this.descrizione=descrizione;
		this.stato=stato;
		this.idCommerciante=idCommerciante;
		this.idCliente=idCliente;
		this.idCorriere=null;
		this.idPuntoDiRitiro=idPuntoDiRitiro;
		this.dataCreazione=new Date();
		this.dataRitiro=null;
		this.dataConsegnaPrevista=null;
	}

	
	public OrdineRiferimento(String codiceRitiro, String descrizione,StatiOrdine stato, Long idCommerciante, Long idCliente ) {
		// TODO Auto-generated constructor stub
		
		this.codiceRitiro=codiceRitiro;
		this.descrizione=descrizione;
		this.stato=stato;
		this.idCommerciante=idCommerciante;
		this.idCliente=idCliente;
		this.idCorriere=null;
		this.idPuntoDiRitiro=null;
		this.dataCreazione=new Date();
		this.dataRitiro=null;
		this.dataConsegnaPrevista=null;
	}
	
	*/
	public OrdineRiferimento(Long id ,String codiceRitiro, String descrizione,StatiOrdine stato, Long idCommerciante, Long idCliente, Long idCorriere,  Long idPuntoDiRitiro,Date dataCreazione ,Date dataRitiro , Date dataConsegnaPrevista ) {
		// TODO Auto-generated constructor stub
		this.ID=id;
		this.codiceRitiro=codiceRitiro;
		this.descrizione=descrizione;
		this.stato=stato;
		this.idCommerciante=idCommerciante;
		this.idCliente=idCliente;
		this.idCorriere=idCorriere;
		this.idPuntoDiRitiro=idPuntoDiRitiro;
		this.dataCreazione=dataCreazione;
		this.dataRitiro=dataRitiro;
		this.dataConsegnaPrevista=dataConsegnaPrevista;
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

	public Long getIdCommerciante() {
		return idCommerciante;
	}

	public Long getIdCorriere() {
		return idCorriere;
	}

	public Long getIdPuntoDiRitiro() {
		return idPuntoDiRitiro;
	}

	public StatiOrdine getStato() {
		return stato;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public Date getDataCreazione() {
		return dataCreazione;
	}

	public Date getDataRitiro() {
		return dataRitiro;
	}

	public Date getDataConsegnaPrevista() {
		return dataConsegnaPrevista;
	}

	
	
}
