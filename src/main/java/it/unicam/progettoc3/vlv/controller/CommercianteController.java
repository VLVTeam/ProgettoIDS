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
public class CommercianteController {

	@Autowired
	CommercianteService commercianteService;
	
	@PreAuthorize("hasRole('AMMINISTRATORE')")
	@PutMapping(value ="/accettaIscrizioneCommerciante/{idCommerciante}")
	public ResponseEntity<?> accettaIscrizioneCommerciante(@PathVariable("idCommerciante") Long idCommerciante) {
		// TODO Auto-generated method stub
		 try {
			commercianteService.accettaIscrizioneCommerciante( idCommerciante);
		//	return new ResponseEntity<>("ISCRIZIONE COMMERCIANTE ACCETTATA",HttpStatus.OK);
			return  new ResponseEntity<>(new Messaggio("ISCRIZIONE COMMERCIANTE ACCETTATA"),HttpStatus.OK);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			//return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			return  new ResponseEntity<>(new Messaggio(e.getMessage()),HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PreAuthorize("hasRole('AMMINISTRATORE')")
	@GetMapping(value="/getCommerciantiDaAccettare")
	public ResponseEntity<List<Utente>> getCommerciantiDaAccettare() {
		// TODO Auto-generated method stub
		return new ResponseEntity<List<Utente>>(commercianteService.getCommerciantiDaAccettare(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('AMMINISTRATORE')")
	@GetMapping(value ="/getCommercianteByIdUtente/{idUtente}")
	public ResponseEntity<?> getCommercianteByIdUtente(@PathVariable("idUtente") Long idUtente)
	{
		try{
		return new ResponseEntity<Commerciante>(commercianteService.getCommercianteByIdUtente(idUtente),HttpStatus.OK);
		}catch(NotFoundException e){
			return  new ResponseEntity<>(new Messaggio(e.getMessage()),HttpStatus.BAD_REQUEST);
			//return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}catch(IllegalArgumentException e){
			return  new ResponseEntity<>(new Messaggio(e.getMessage()),HttpStatus.BAD_REQUEST);
			//return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('CLIENTE')")
	@GetMapping(value="/getCommercianti")
	public ResponseEntity<List<Commerciante>> getCommercianti() {
		// TODO Auto-generated method stub
		return new ResponseEntity<List<Commerciante>>(commercianteService.getCommercianti(),HttpStatus.OK);
	}
	
}
