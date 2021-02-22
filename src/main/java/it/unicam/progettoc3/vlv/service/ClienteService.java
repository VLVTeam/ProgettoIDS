package it.unicam.progettoc3.vlv.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import it.unicam.progettoc3.vlv.entity.utenti.Cliente;

import it.unicam.progettoc3.vlv.repository.ClienteRepository;
import it.unicam.progettoc3.vlv.repository.UtenteRepository;

@Service
@Transactional
/**
 * La classe ClienteService definisce precisamente il funzionamento
 * dei metodi presenti anche nella classe controller, fornendoli appunto alla classe ClienteController.
 */
public class ClienteService {

	// collegamenti alle repository
	@Autowired
	UtenteRepository utenteRepository;

	@Autowired
	ClienteRepository clienteRepository;
	
	
	/** metodo per salvare il cliente nella repository */
	public void save(Cliente cliente)
	{
		clienteRepository.save(cliente);
	}
	
	
	public List<Cliente> getClienti() {
		// TODO Auto-generated method stub
		// crea una lista di clienti e la riempie con tutti i clienti esistenti, poi la restituisce, puo' anche essere null
		List<Cliente> clienti= new ArrayList<>();
		Iterable<Cliente> iteratore = clienteRepository.findAll();
		
		iteratore.forEach(Cliente -> clienti.add(Cliente));
		if (clienti.isEmpty()){return null;}
		else{return clienti;}
	}
	
}
