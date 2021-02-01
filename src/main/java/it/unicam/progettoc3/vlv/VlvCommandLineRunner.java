package it.unicam.progettoc3.vlv;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import it.unicam.progettoc3.vlv.entity.utenti.Amministratore;
import it.unicam.progettoc3.vlv.entity.utenti.Cliente;
import it.unicam.progettoc3.vlv.repository.AmministratoreRepository;

import it.unicam.progettoc3.vlv.repository.ClienteRepository;


@Component
public class VlvCommandLineRunner implements CommandLineRunner{

	@Autowired
	AmministratoreRepository amministratoreRepository;
	
	@Autowired
	ClienteRepository  clienteRepository;
	
	
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		if(!amministratoreRepository.findById(1L).isPresent())
		{
		
		amministratoreRepository.save( new Amministratore( "Mario", "Rossi", "mario@rossi.it","password"));
		}
		if(!clienteRepository.findById(1L).isPresent())
		{
		
		clienteRepository.save( new Cliente("nico", "cognome", "indirizzo","nico@asciutti.it", "password"));
		}
		
		
		
	}
	
}
