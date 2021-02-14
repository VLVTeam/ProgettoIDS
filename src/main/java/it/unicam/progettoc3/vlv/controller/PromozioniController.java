package it.unicam.progettoc3.vlv.controller;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

import it.unicam.progettoc3.vlv.entity.dto.NuovaPromozione;
import it.unicam.progettoc3.vlv.entity.elementi.Promozione;
import it.unicam.progettoc3.vlv.entity.enumeratori.CategorieMerceologiche;
import it.unicam.progettoc3.vlv.service.PromozioniService;
import javassist.NotFoundException;


@RestController
@RequestMapping("/gestorePromozioni")
@CrossOrigin(origins = "http://localhost:8080")
public class PromozioniController {

	@Autowired 
	PromozioniService promozioniService;
	
	


	@PostMapping(value = "/addPromozione")
	@PreAuthorize("hasRole('COMMERCIANTE')")
	public ResponseEntity<String> addPromozione(@Valid @RequestBody NuovaPromozione promozione, BindingResult bindingResult , Authentication authentication) {
		// TODO Auto-generated method stub
		
		if(bindingResult.hasErrors())
			return new ResponseEntity<String>("controlla campi" , HttpStatus.BAD_REQUEST);
		
		
		String emailCommerciante=authentication.getName();
		try {
			 promozioniService.addPromozione(promozione,emailCommerciante);

		
			return  new ResponseEntity<>("PROMOZIONE AGGIUNTA",HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			return  new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}catch(NotFoundException e){
		return  new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	
	
	@DeleteMapping(value = "/deletePromozione/{idPromozione}")
	@PreAuthorize("hasRole('COMMERCIANTE')")
	public ResponseEntity<String> deletePromozione(@PathVariable("idPromozione") Long idPromozione)
	{
		try {
			 promozioniService.deletePromozione(idPromozione);

		
			return  new ResponseEntity<>("PROMOZIONE RIMOSSA",HttpStatus.OK);
		} catch (NotFoundException e) {
			// TODO: handle exception
			return  new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	
	

	@GetMapping(value = "/getPromozioniFiltrate/{categoriaMerceologica}")
	@PreAuthorize("hasRole('CLIENTE')")
	public ResponseEntity<?> getPromozioniFiltrate(@PathVariable("categoriaMerceologica") CategorieMerceologiche categoriaMerceologica) {
		// TODO Auto-generated method stub
		try {
			return new ResponseEntity<List<Promozione>>(promozioniService.getPromozioniFiltrate(categoriaMerceologica),HttpStatus.OK);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			return  new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/getPromozioniCommerciante")
	@PreAuthorize("hasRole('COMMERCIANTE')")
	public ResponseEntity<?> getPromozioniCommerciante(Authentication authentication) {
		// TODO Auto-generated method stub
		String emailCommerciante = authentication.getName();
		try {
			
			return new ResponseEntity<List<Promozione>>(promozioniService.getPromozioniCommerciante(emailCommerciante),HttpStatus.OK);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			return  new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/getPromozioniNonScadute")
	@PreAuthorize("hasRole('CLIENTE')")
	public ResponseEntity<List<Promozione>> getPromozioniNonScadute() {
		// TODO Auto-generated method stub
		
		return new ResponseEntity<List<Promozione>>(promozioniService.getPromozioniNonScadute(),HttpStatus.OK);
	}


/*
	@PutMapping(value = "/modificaPromozione/{idPromozione}/{descrizione}/{dataInizio}/{dataFine}")
	@PreAuthorize("hasRole('COMMERCIANTE')")
	public ResponseEntity<String> modificaPromozione(@PathVariable(name ="idPromozione", required =false) Long idPromozione, @PathVariable(name = "descrizione",required=false)String descrizione,@PathVariable(name = "dataInizio",required=false) Date dataInizio,@PathVariable(name="dataFine",required=false) Date dataFine) {
		// TODO Auto-generated method stub
		 try {
			promozioniService.modificaPromozione(idPromozione, descrizione, dataInizio, dataFine);
			return  new ResponseEntity<>("PROMOZIONE MODIFICATA",HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			return  new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
			
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			return  new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
			
		}
	}
*/
	

}
