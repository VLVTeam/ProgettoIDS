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
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	
	@PreAuthorize("hasRole('COMMERCIANTE')")
	@GetMapping(value="/getClienti")
	
	public ResponseEntity<List<Cliente>> getClienti() {
		// TODO Auto-generated method stub
		return new ResponseEntity<List<Cliente>>(clienteService.getClienti(),HttpStatus.OK);
	}

}
