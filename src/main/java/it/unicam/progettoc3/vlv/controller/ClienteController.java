package it.unicam.progettoc3.vlv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import it.unicam.progettoc3.vlv.entity.utenti.Cliente;
import it.unicam.progettoc3.vlv.service.ClienteService;

@RestController
@RequestMapping("/gestoreClienti")
@CrossOrigin(origins = "http://localhost:4200")
/**
 * Questa classe definisce solo il metodo getClienti, che puo' essere richiamato solo da un commerciante
 */
public class ClienteController {

	// collegamento alla classe service corrispondente
	@Autowired
	ClienteService clienteService;
	
	@PreAuthorize("hasRole('COMMERCIANTE')")
	@GetMapping(value="/getClienti")
	/** metodo che restituisce la lista di tutti i clienti, attraverso la classe 'ClienteService' */
	public ResponseEntity<List<Cliente>> getClienti() {
		// TODO Auto-generated method stub
		return new ResponseEntity<List<Cliente>>(clienteService.getClienti(),HttpStatus.OK);
	}

}
