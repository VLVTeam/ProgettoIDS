package it.unicam.progettoc3.vlv;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



import it.unicam.progettoc3.vlv.repository.AmministratoreRepository;

import it.unicam.progettoc3.vlv.repository.ClienteRepository;

import it.unicam.progettoc3.vlv.security.service.AuthService;



@Component
public class VlvCommandLineRunner implements CommandLineRunner{

	@Autowired
	AmministratoreRepository amministratoreRepository;
	
	@Autowired
	ClienteRepository  clienteRepository;
	
	@Autowired
	AuthService authService;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		

		if(!amministratoreRepository.existsById(1L)){
		authService.nuovoAmministratore("prova@prova.it", "prova");
		}
		
		
		
	}
	
}
