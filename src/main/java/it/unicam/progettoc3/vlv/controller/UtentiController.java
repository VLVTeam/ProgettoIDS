package it.unicam.progettoc3.vlv.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.progettoc3.vlv.entity.enumeratori.CategorieMerceologiche;
import it.unicam.progettoc3.vlv.entity.utenti.Cliente;
import it.unicam.progettoc3.vlv.entity.utenti.Commerciante;
import it.unicam.progettoc3.vlv.entity.utenti.Corriere;
import it.unicam.progettoc3.vlv.service.UtentiService;

@RestController
public class UtentiController implements IUtenti {
	
	@Autowired
	UtentiService utentiService;

	@GetMapping(value ="/setStatoIscrizioneCommerciante")
	@Override
	public ResponseEntity<String> setStatoIscrizioneCommerciante(Commerciante commerciante, boolean stato) {
		// TODO Auto-generated method stub
		return utentiService.setStatoIscrizioneCommerciante(commerciante, stato);
	}
	
	@GetMapping(value ="/setStatoIscrizioneCorriere")
	@Override
	public ResponseEntity<String> setStatoIscrizioneCorriere(Corriere corriere, boolean stato) {
		// TODO Auto-generated method stub
		return utentiService.setStatoIscrizioneCorriere(corriere, stato);
	}
	
	@PutMapping(value ="/addCommerciante")
	@Override
	public ResponseEntity<String> addCommerciante(Commerciante commerciante) {
		// TODO Auto-generated method stub
		return utentiService.addCommerciante(commerciante);
	}

	@PutMapping(value ="/addCliente")
	@Override
	public ResponseEntity<String> addCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return utentiService.addCliente(cliente);
	}

	@PutMapping(value ="/addCorriere")
	@Override
	public ResponseEntity<String> addCorriere(Corriere corriere) {
		// TODO Auto-generated method stub
		return utentiService.addCorriere(corriere);
	}

	@GetMapping(value="/getPuntiVenditaFiltrati")
	@Override
	public List<Commerciante> getPuntiVenditaFiltrati(CategorieMerceologiche categoriaMerceologica) {
		// TODO Auto-generated method stub
		return utentiService.getPuntiVenditaFiltrati(categoriaMerceologica);
	}

	@GetMapping(value="/getPuntiVendita")
	@Override
	public List<Commerciante> getPuntiVendita() {
		// TODO Auto-generated method stub
		return utentiService.getPuntiVendita();
	}

	@GetMapping(value="/getCommerciantiDaAccettare")
	@Override
	public List<Commerciante> getCommerciantiDaAccettare() {
		// TODO Auto-generated method stub
		return utentiService.getCommerciantiDaAccettare();
	}

	@GetMapping(value="/getCorrieri")
	@Override
	public List<Corriere> getCorrieri() {
		// TODO Auto-generated method stub
		return utentiService.getCorrieri();
	}

	@GetMapping(value="/getCorrieriDaAccettare")
	@Override
	public List<Corriere> getCorrieriDaAccettare() {
		// TODO Auto-generated method stub
		return utentiService.getCorrieriDaAccettare();
	}

	@GetMapping(value="/getClienti")
	@Override
	public List<Cliente> getClienti() {
		// TODO Auto-generated method stub
		return utentiService.getClienti();
	}

}
