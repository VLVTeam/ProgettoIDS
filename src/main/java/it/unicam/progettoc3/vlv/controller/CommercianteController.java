package it.unicam.progettoc3.vlv.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import it.unicam.progettoc3.vlv.entity.utenti.Commerciante;
import it.unicam.progettoc3.vlv.entity.utenti.Utente;
import it.unicam.progettoc3.vlv.service.CommercianteService;
import it.unicam.progettoc3.vlv.utils.Messaggio;
import javassist.NotFoundException;

@RestController
@RequestMapping("/gestoreCommercianti")
@CrossOrigin(origins = "http://localhost:4200")
/**
 * Questa classe definisce tutti i metodi relativi
 * ai commercianti e, attraverso la loro invocazione, delega la loro precisa esecuzione alla classe service
 * corrispondente.
 */
public class CommercianteController {

	
	
	// collegamento alla classe service corrispondente
	@Autowired
	CommercianteService commercianteService;
	
	/** metodo per cambiare lo stato dell'iscrizione di un commerciante da false a true, puo' farlo solo l'amministratore, attraverso la classe 'CommercianteService' */
	@PreAuthorize("hasRole('AMMINISTRATORE')")
	@PutMapping(value ="/accettaIscrizioneCommerciante/{idCommerciante}")
	public ResponseEntity<?> accettaIscrizioneCommerciante(@PathVariable("idCommerciante") Long idCommerciante) {
		// TODO Auto-generated method stub
		 try {
			commercianteService.accettaIscrizioneCommerciante( idCommerciante);
			return new ResponseEntity<>(new Messaggio("ISCRIZIONE COMMERCIANTE ACCETTATA"),HttpStatus.OK);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(new Messaggio(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PreAuthorize("hasRole('AMMINISTRATORE')")
	@GetMapping(value="/getCommerciantiDaAccettare")
	/** metodo che fornisce la lista di commercianti le quali iscrizioni non sono state ancora accettate, attraverso la classe 'CommercianteService' */
	public ResponseEntity<List<Utente>> getCommerciantiDaAccettare() {
		// TODO Auto-generated method stub
		return new ResponseEntity<List<Utente>>(commercianteService.getCommerciantiDaAccettare(), HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('AMMINISTRATORE')")
	@GetMapping(value ="/getCommercianteByIdUtente/{idUtente}")
	/** metodo che, dato un'id utente, se il ruolo dell'utente trovato corrisponde a ruolo commerciante, restituisce questo commerciante, attraverso la classe 'CommercianteService' */
	public ResponseEntity<?> getCommercianteByIdUtente(@PathVariable("idUtente") Long idUtente)
	{
		try{
		return new ResponseEntity<Commerciante>(commercianteService.getCommercianteByIdUtente(idUtente),HttpStatus.OK);
		}catch(NotFoundException e){
			return new ResponseEntity<>(new Messaggio(e.getMessage()), HttpStatus.BAD_REQUEST);
		}catch(IllegalArgumentException e){
			return new ResponseEntity<>(new Messaggio(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PreAuthorize("hasRole('CLIENTE')")
	@GetMapping(value="/getCommercianti")
	/** metodo che fornisce la lista di tutti i commercianti, attraverso la classe 'CommercianteService' */
	public ResponseEntity<List<Commerciante>> getCommercianti() {
		// TODO Auto-generated method stub
		return new ResponseEntity<List<Commerciante>>(commercianteService.getCommercianti(),HttpStatus.OK);
	}
	
}
