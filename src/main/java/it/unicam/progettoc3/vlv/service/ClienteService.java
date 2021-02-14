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
public class ClienteService {

	
	@Autowired
	UtenteRepository utenteRepository;

	@Autowired
	ClienteRepository clienteRepository;
	
	
	
	public void save(Cliente cliente)
	{
		clienteRepository.save(cliente);
	}
	
	
	
	public List<Cliente> getClienti() {
		// TODO Auto-generated method stub
		List<Cliente> clienti= new ArrayList<>();
		Iterable<Cliente> iteratore = clienteRepository.findAll();
		
		iteratore.forEach(Cliente -> clienti.add(Cliente));
		if (clienti.isEmpty()){return null;}
		else{return clienti;}
	}
	
}
