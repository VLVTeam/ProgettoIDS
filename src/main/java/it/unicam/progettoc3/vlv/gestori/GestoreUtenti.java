package it.unicam.progettoc3.vlv.gestori;



import java.util.ArrayList;
import java.util.List;

import it.unicam.progettoc3.vlv.utenti.Amministratore;
import it.unicam.progettoc3.vlv.utenti.Cliente;
import it.unicam.progettoc3.vlv.utenti.Commerciante;
import it.unicam.progettoc3.vlv.utenti.Corriere;

public class GestoreUtenti implements IGestoreUtenti{
	
	private Amministratore amministratore;
	
	private List<Commerciante> commercianti = new ArrayList<>();
	private List<Cliente> clienti = new ArrayList<>();
	private  List<Corriere> corrieri = new ArrayList<>();
	

	public Amministratore getAmministratore() {
		return amministratore;
	}

	public void setAmministratore(Amministratore amministratore) {
		this.amministratore = amministratore;
	}

	public List<Commerciante> getCommercianti() {
		return commercianti;
	}

	public List<Cliente> getClienti() {
		return clienti;
	}

	public List<Corriere> getCorrieri() {
		return corrieri;
	}

	
	
	@Override
	public void setStatoIscrizioneCommerciante(Commerciante commerciante, boolean stato) {
		// TODO Auto-generated method stub
		commerciante.setStatoIscrizione(stato);
	}

	@Override
	public void setStatoIscrizioneCorriere(Corriere corriere, boolean stato) {
		// TODO Auto-generated method stub
		corriere.setStatoIscrizione(stato);
	}

	@Override
	public void addCommerciante(Commerciante commerciante) {
		// TODO Auto-generated method stub
		
		if(!commercianti.contains(commerciante)){commercianti.add(commerciante);}
		else{System.out.println("Commerciante  gia presente");}
	}

	@Override
	public void addCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		if(!clienti.contains(cliente)){clienti.add(cliente);}
		else{System.out.println("Cliente gia presente");}
	}

	@Override
	public void addCorriere(Corriere corriere) {
		// TODO Auto-generated method stub
		if(!corrieri.contains(corriere)){corrieri.add(corriere);}
		else{System.out.println("Corriere gia presente");}
	}

	

}
