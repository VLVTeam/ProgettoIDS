package it.unicam.progettoc3.vlv.controller;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import it.unicam.progettoc3.vlv.entity.enumeratori.CategorieMerceologiche;
import it.unicam.progettoc3.vlv.entity.utenti.Cliente;
import it.unicam.progettoc3.vlv.entity.utenti.Commerciante;
import it.unicam.progettoc3.vlv.entity.utenti.Corriere;

public interface IUtenti {
	
	
	
	public ResponseEntity<String> setStatoIscrizioneCommerciante(@RequestParam Long  idCommerciante,  @RequestParam boolean stato);
	
	public ResponseEntity<String> setStatoIscrizioneCorriere(@RequestParam Long idCorriere, @RequestParam boolean stato);
	
	public ResponseEntity<String> addCommerciante(@RequestBody Commerciante commerciante);
	
	public ResponseEntity<String> addCliente(@RequestBody Cliente cliente);
	
	public ResponseEntity<String> addCorriere(@RequestBody Corriere corriere);
	
	public List<Commerciante> getPuntiVenditaFiltrati(@RequestParam CategorieMerceologiche categoriaMerceologica);
	
	public List<Commerciante> getPuntiVendita();
	
	public List<Commerciante> getCommerciantiDaAccettare();
	
	public List<Corriere> getCorrieri();
	
	public List<Corriere> getCorrieriDaAccettare();
	
	public List<Cliente> getClienti();
}
