package it.unicam.progettoc3.vlv.controller;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import it.unicam.progettoc3.vlv.entity.enumeratori.CategorieMerceologiche;
import it.unicam.progettoc3.vlv.entity.utenti.Cliente;
import it.unicam.progettoc3.vlv.entity.utenti.Commerciante;
import it.unicam.progettoc3.vlv.entity.utenti.Corriere;

public interface IUtenti {
	
	
	
	public ResponseEntity<String> setStatoIscrizioneCommerciante(@RequestParam Commerciante commerciante,  @RequestParam boolean stato);
	
	public ResponseEntity<String> setStatoIscrizioneCorriere(@RequestParam Corriere corriere, @RequestParam boolean stato);
	
	public ResponseEntity<String> addCommerciante(@RequestParam Commerciante commerciante);
	
	public ResponseEntity<String> addCliente(@RequestParam Cliente cliente);
	
	public ResponseEntity<String> addCorriere(@RequestParam Corriere corriere);
	
	public List<Commerciante> getPuntiVenditaFiltrati(@RequestParam CategorieMerceologiche categoriaMerceologica);
	
	public List<Commerciante> getPuntiVendita();
	
	public List<Commerciante> getCommerciantiDaAccettare();
	
	public List<Corriere> getCorrieri();
	
	public List<Corriere> getCorrieriDaAccettare();
	
	public List<Cliente> getClienti();
}
