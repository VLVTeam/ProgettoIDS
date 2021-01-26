package it.unicam.progettoc3.vlv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.progettoc3.vlv.entity.elementi.Ordine;
import it.unicam.progettoc3.vlv.entity.utenti.Cliente;
import it.unicam.progettoc3.vlv.service.OrdiniService;


@RestController
public class OrdiniController implements IOrdini{

	
	@Autowired
	OrdiniService ordiniService;
	
	
	@PutMapping(value= "/addOrdine")
	@Override
	public ResponseEntity<String> addOrdine(@RequestParam Ordine ordine) {
		// TODO Auto-generated method stub
		return  ordiniService.addOrdine(ordine);
	}

	@DeleteMapping(value = "/removeOrdine")
	@Override
	public ResponseEntity<String> removeOrdine(@RequestParam Ordine ordine) {
		// TODO Auto-generated method stub
		 return ordiniService.removeOrdine(ordine);
	}
	
	@GetMapping(value = "/getOrdini")
	@Override
	public List<Ordine> getOrdiniCliente(@RequestParam Cliente cliente) {
		// TODO Auto-generated method stub
		return ordiniService.getOrdiniCliente(cliente);
	}

	@PostMapping(value = "/ritiraOrdine")
	@Override
	public ResponseEntity<String> ritiraOrdine(@RequestParam Ordine ordine , @RequestParam String codiceRitiro) {
		// TODO Auto-generated method stub
		return ordiniService.ritiraOrdine(ordine , codiceRitiro);
	}

}
