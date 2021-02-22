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

import it.unicam.progettoc3.vlv.entity.utenti.Corriere;
import it.unicam.progettoc3.vlv.entity.utenti.Utente;
import it.unicam.progettoc3.vlv.service.CorriereService;
import it.unicam.progettoc3.vlv.utils.Messaggio;
import javassist.NotFoundException;

@RestController
@RequestMapping("/gestoreCorrieri")
@CrossOrigin(origins = "http://localhost:4200")
/**
 * Questa classe definisce tutti i metodi relativi
 * ai corrieri e, attraverso la loro invocazione, delega la loro precisa esecuzione alla classe service
 * corrispondente.
 */
public class CorriereController {

	// collegamento alla classe service corrispondente
	@Autowired
	CorriereService corriereService;
	
	@PreAuthorize("hasRole('AMMINISTRATORE')")
	@PutMapping(value ="/accettaIscrizioneCorriere/{idCorriere}")
	/** metodo per cambiare lo stato dell'iscrizione di un corriere da false a true, puo' farlo solo l'amministratore, attraverso la classe 'CorriereService' */
	public ResponseEntity<?> accettaIscrizioneCorriere(@PathVariable("idCorriere") Long idCorriere) {
		// TODO Auto-generated method stub
		 try {
			corriereService.accettaIscrizioneCorriere(idCorriere);
			return new ResponseEntity<>(new Messaggio("ISCRIZIONE CORRIERE ACCETTATA"),HttpStatus.OK);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(new Messaggio(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PreAuthorize("hasRole('AMMINISTRATORE')")
	@GetMapping(value="/getCorrieriDaAccettare")
	/** metodo che fornisce la lista di corrieri le quali iscrizioni non sono state ancora accettate, attraverso la classe 'CorriereService' */
	public ResponseEntity<List<Utente>> getCorrieriDaAccettare() {
		// TODO Auto-generated method stub
		return new ResponseEntity<List<Utente>>(corriereService.getCorrieriDaAccettare(),HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('AMMINISTRATORE')")
	@GetMapping(value ="/getCorriereByIdUtente/{idUtente}")
	/** metodo che, dato un'id utente, se il ruolo dell'utente trovato corrisponde a ruolo corriere, restituisce questo commerciante, attraverso la classe 'CorriereService' */
	public ResponseEntity<?> getCorriereByIdUtente(@PathVariable("idUtente") Long idUtente)
	{
		
		try{
			return new ResponseEntity<Corriere>(corriereService.getCorriereByIdUtente(idUtente),HttpStatus.OK);
			}catch(NotFoundException e){
				return new ResponseEntity<>(new Messaggio(e.getMessage()), HttpStatus.BAD_REQUEST);
			}catch(IllegalArgumentException e){
				return new ResponseEntity<>(new Messaggio(e.getMessage()), HttpStatus.BAD_REQUEST);
			}
		
	}
	
	
}