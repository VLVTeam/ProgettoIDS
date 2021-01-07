package it.unicam.progettoc3.vlv.utenti;

import it.unicam.progettoc3.vlv.enumeratori.CategorieMerceologiche;

public class Commerciante {
	private int ID;
	private CategorieMerceologiche categoriaMerceologica;
	private String indirizzoPuntoVendita;
	private String nomePuntoVendita;
	private boolean statoIscrizione;
	
	
	public Commerciante(int ID, CategorieMerceologiche categoriaMerceologica, String indirizzoPuntoVendita,
			String nomePuntoVendita) {
		
		this.ID = ID;
		this.categoriaMerceologica = categoriaMerceologica;
		this.indirizzoPuntoVendita = indirizzoPuntoVendita;
		this.nomePuntoVendita = nomePuntoVendita;
		this.statoIscrizione = false;
	}


	public int getID() {
		return ID;
	}


	


	public CategorieMerceologiche getCategoriaMerceologica() {
		return categoriaMerceologica;
	}




	public String getIndirizzoPuntoVendita() {
		return indirizzoPuntoVendita;
	}


	public void setIndirizzoPuntoVendita(String indirizzoPuntoVendita) {
		this.indirizzoPuntoVendita = indirizzoPuntoVendita;
	}


	public String getNomePuntoVendita() {
		return nomePuntoVendita;
	}



	public boolean isStatoIscrizione() {
		return statoIscrizione;
	}


	public void setStatoIscrizione(boolean statoIscrizione) {
		this.statoIscrizione = statoIscrizione;
	}
	
	
	
	
	
	
	
	

}
