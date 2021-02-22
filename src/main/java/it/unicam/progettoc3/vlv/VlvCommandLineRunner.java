package it.unicam.progettoc3.vlv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import it.unicam.progettoc3.vlv.repository.AmministratoreRepository;

import it.unicam.progettoc3.vlv.repository.ClienteRepository;

import it.unicam.progettoc3.vlv.security.service.AuthService;
import it.unicam.progettoc3.vlv.service.AmministratoreService;


/**
 * Questa classe viene istanziata subito dopo che l'applicazione viene eseguita, in quanto è l'unica
 * ad implementare l'interfaccia fornita da springboot "CommandLineRunner". Per tanto il metodo che è
 * descritto in questa classe è il primo metodo, relativo al progetto, eseguito nell'applicazione.
 * Il metodo si limita a controllare se esiste già un amministratore (deve essere atomico); se esiste 
 * non viene applicata nessuna modifica, ma se ancora non è stato inserito, viene salvato l'amministratore
 * prestabilito.
 */
@Component
@SpringBootApplication
public class VlvCommandLineRunner implements CommandLineRunner{

	@Autowired
	AmministratoreRepository amministratoreRepository;
	
	@Autowired
	ClienteRepository  clienteRepository;
	
	@Autowired
	AuthService authService;
	
	@Autowired
	AmministratoreService amministratoreService;
	
	/**
	 * controlla nella repository dell'amministratore se è presente l'amministratore, altrimenti lo crea
	 */
	@Override
	public void run(String... args) throws Exception {
	// TODO Auto-generated method stub

		if(!amministratoreService.existAdmin()){
			authService.nuovoAmministratore("amministratore@prova.it", "amministratore");
		}
		
	}
	
}
