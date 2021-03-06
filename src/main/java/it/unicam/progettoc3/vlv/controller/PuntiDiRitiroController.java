package it.unicam.progettoc3.vlv.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.progettoc3.vlv.entity.elementi.PuntoDiRitiro;
import it.unicam.progettoc3.vlv.service.PuntiDiRitiroService;
import it.unicam.progettoc3.vlv.utils.Messaggio;
import javassist.NotFoundException;

@RestController
@RequestMapping("/gestorePuntiDiRitiro")
@CrossOrigin(origins = "http://localhost:4200")
/**
 *  Questa classe definisce tutti i metodi relativi
 *  ai punti di ritiro e, attraverso la loro invocazione, delega la loro precisa esecuzione alla classe service
 *  corrispondente.
 */
public class PuntiDiRitiroController  {

	// collegamento alla classe service corrispondente
	@Autowired
	PuntiDiRitiroService puntiDiRitiroService;
	
	@PostMapping(value="/addPuntoDiRitiro")
	@PreAuthorize("hasRole('AMMINISTRATORE')")
	/** metodo per aggiungere un punto di ritiro alla lista dei punti di ritiro, attraverso la classe 'PuntiDiRitiroService' */
	public ResponseEntity<?> addPuntoDiRitiro(@Valid @RequestBody PuntoDiRitiro puntoDiRitiro , BindingResult bindingResult) {
		// TODO Auto-generated method stub
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(new Messaggio("controlla campi") , HttpStatus.BAD_REQUEST);
		 puntiDiRitiroService.addPuntoDiRitiro(puntoDiRitiro);
		 return new ResponseEntity<>(new Messaggio("PUNTO DI RITIRO AGGIUNTO"),HttpStatus.OK);
	}

	
	@DeleteMapping(value="/deletePuntoDiRitiro/{idPuntoDiRitiro}")
	@PreAuthorize("hasRole('AMMINISTRATORE')")
	/** metodo per eliminare, se esiste, un punto di ritiro, attraverso la classe 'PuntiDiRitiroService' */
	public ResponseEntity<?> deletePuntoDiRitiro(@PathVariable("idPuntoDiRitiro") Long idPuntoDiRitiro) {
		// TODO Auto-generated method stub
		 try {
			puntiDiRitiroService.deletePuntoDiRitiro(idPuntoDiRitiro);
			return new ResponseEntity<>(new Messaggio("PUNTO DI RITIRO RIMOSSO"),HttpStatus.OK);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			return  new ResponseEntity<>(new Messaggio(e.getMessage()),HttpStatus.BAD_REQUEST);		}
		 
	}

	@GetMapping(value="/getPuntiDiRitiro")
	@PreAuthorize("hasRole('COMMERCIANTE') OR hasRole('AMMINISTRATORE')")
	/** metodo che restituisce la lista di tutti i punti di ritiro presenti, attraverso la classe 'PuntiDiRitiroService' */
	public ResponseEntity<List<PuntoDiRitiro>> getPuntiDiRitiro() {
		// TODO Auto-generated method stub
		return  new ResponseEntity<List<PuntoDiRitiro>>(puntiDiRitiroService.getPuntiDiRitiro(), HttpStatus.OK);
	}

}