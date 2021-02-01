package it.unicam.progettoc3.vlv.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import it.unicam.progettoc3.vlv.entity.riferimenti.OrdineRiferimento;
import it.unicam.progettoc3.vlv.service.OrdiniService;


@RestController
@RequestMapping("/gestoreOrdini")
public class OrdiniController implements IOrdini{

	
	@Autowired
	OrdiniService ordiniService;

	
	@PostMapping(value= "/addOrdine")
	@Override
	public ResponseEntity<String> addOrdine(@RequestBody OrdineRiferimento ordine) {
		// TODO Auto-generated method stub
		return  ordiniService.addOrdine(ordine);
	}

	
	
	@GetMapping(value = "/getOrdini")
	@Override
	public List<OrdineRiferimento> getOrdiniCliente(@RequestParam Long idCliente) {
		// TODO Auto-generated method stub
		return ordiniService.getOrdiniCliente(idCliente);
	}

	@PutMapping(value = "/ritiraOrdine")
	@Override
	public ResponseEntity<String> ritiraOrdine(@RequestParam Long idOrdine , @RequestParam String codiceRitiro) {
		// TODO Auto-generated method stub
		return ordiniService.ritiraOrdine(idOrdine , codiceRitiro);
	}

	@GetMapping(value = "/getOrdiniLiberi")
	@Override
	public List<OrdineRiferimento> getOrdiniLiberi() {
		// TODO Auto-generated method stub
		return ordiniService.getOrdiniLiberi();
	}

	@GetMapping(value = "/getOrdiniDaRitirare")
	@Override
	public List<OrdineRiferimento> getOrdiniDaRitirare(@RequestParam Long idCorriere) {
		// TODO Auto-generated method stub
		return ordiniService.getOrdiniDaRitirare(idCorriere);
	}

	@GetMapping(value = "/getOrdiniInTransito")
	@Override
	public List<OrdineRiferimento> getOrdiniInTransito(@RequestParam Long idCorriere) {
		// TODO Auto-generated method stub
		return ordiniService.getOrdiniInTransito(idCorriere);
	}

	@PutMapping(value = "/setPresaInCaricoOrdine")
	@Override
	public ResponseEntity<String> setPresaInCaricoOrdine(@RequestParam Long idOrdine, @RequestParam Long idCorriere , @RequestParam Date dataPrevistaRitiro) {
		// TODO Auto-generated method stub
		return ordiniService.setPresaInCaricoOrdine(idOrdine,idCorriere,  dataPrevistaRitiro);
	}

	@PutMapping(value = "/setDataConsegnaPrevista")
	@Override
	public ResponseEntity<String> setDataConsegnaPrevista(@RequestParam Long idOdine,@RequestParam Date dataConsegnaPrevista) {
		// TODO Auto-generated method stub
		return ordiniService.setDataConsegnaPrevista(idOdine, dataConsegnaPrevista);
	}

	@PutMapping(value = "/setOrdineConsegnato")
	@Override
	public ResponseEntity<String> setOrdineConsegnato(@RequestParam Long idOrdine) {
		// TODO Auto-generated method stub
		return ordiniService.setOrdineConsegnato(idOrdine);
	}

	@PutMapping(value = "/setOrdineProntoPerIlRitiro")
	@Override
	public ResponseEntity<String> setOrdineProntoPerIlRitiro(@RequestParam Long idOrdine) {
		// TODO Auto-generated method stub
		return ordiniService.setOrdineProntoPerIlRitiro(idOrdine);
	}

}
