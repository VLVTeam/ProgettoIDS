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
import javassist.NotFoundException;

@RestController
@RequestMapping("/gestoreCorrieri")
@CrossOrigin(origins = "http://localhost:8080")
public class CorriereController {

	@Autowired
	CorriereService corriereService;
	
	@PreAuthorize("hasRole('AMMINISTRATORE')")
	@PutMapping(value ="/accettaIscrizioneCorriere/{idCorriere}")
	
	public ResponseEntity<String> accettaIscrizioneCorriere(@PathVariable("idCorriere") Long idCorriere) {
		// TODO Auto-generated method stub
		 try {
			corriereService.accettaIscrizioneCorriere(idCorriere);
			return new ResponseEntity<>("ISCRIZIONE CORRIERE ACCETTATA",HttpStatus.OK);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	@PreAuthorize("hasRole('AMMINISTRATORE')")
	@GetMapping(value="/getCorrieriDaAccettare")
	
	public ResponseEntity<List<Utente>> getCorrieriDaAccettare() {
		// TODO Auto-generated method stub
		return new ResponseEntity<List<Utente>>(corriereService.getCorrieriDaAccettare(),HttpStatus.OK);
	}
	
	
	
	@PreAuthorize("hasRole('AMMINISTRATORE')")
	@GetMapping(value ="/getCorriereByIdUtente/{idUtente}")
	public ResponseEntity<?> getCorriereByIdUtente(@PathVariable("idUtente") Long idUtente)
	{
		
		
		try{
			return new ResponseEntity<Corriere>(corriereService.getCorriereByIdUtente(idUtente),HttpStatus.OK);
			}catch(NotFoundException e){
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}catch(IllegalArgumentException e){
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
	}
}
