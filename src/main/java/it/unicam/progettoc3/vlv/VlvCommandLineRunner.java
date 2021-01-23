package it.unicam.progettoc3.vlv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.unicam.progettoc3.vlv.entity.utenti.Amministratore;
import it.unicam.progettoc3.vlv.entity.utenti.AmministratoreRepository;

@Component
public class VlvCommandLineRunner implements CommandLineRunner{

	@Autowired
	AmministratoreRepository repository;
	
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		repository.save( new Amministratore( "Mario", "Rossi"));
	}
	
}
