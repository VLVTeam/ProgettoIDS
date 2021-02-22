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
import it.unicam.progettoc3.vlv.utils.Messaggio;
import javassist.NotFoundException;


@RestController
@RequestMapping("/gestorePromozioni")
@CrossOrigin(origins = "http://localhost:4200")
/**
 * Questa classe definisce tutti i metodi relativi
 * alle promozioni e, attraverso la loro invocazione, delega la loro precisa esecuzione alla classe service
 * corrispondente.
 */
public class PromozioniController {

	// collegamento alla classe service corrispondente
	@Autowired 
	PromozioniService promozioniService;

	@PostMapping(value = "/addPromozione")
	@PreAuthorize("hasRole('COMMERCIANTE')")
	/** metodo che aggiunge una promozione, nella lista delle promozioni, attraverso la classe 'PromozioniService' */
	public ResponseEntity<?> addPromozione(@Valid @RequestBody NuovaPromozione promozione, BindingResult bindingResult , Authentication authentication) {
		// TODO Auto-generated method stub
		
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(new Messaggio("controlla campi") , HttpStatus.BAD_REQUEST);
		
		
		String emailCommerciante=authentication.getName();
		try {
			promozioniService.addPromozione(promozione,emailCommerciante);		
			return  new ResponseEntity<>(new Messaggio("PROMOZIONE AGGIUNTA"),HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			return  new ResponseEntity<>(new Messaggio(e.getMessage()),HttpStatus.BAD_REQUEST);
		}catch(NotFoundException e){
			return  new ResponseEntity<>(new Messaggio(e.getMessage()),HttpStatus.BAD_REQUEST);
		}
		
	}

	
	@DeleteMapping(value = "/deletePromozione/{idPromozione}")
	@PreAuthorize("hasRole('COMMERCIANTE')")
	/** metodo che rimuove una promozione, se esiste, dalla lista delle promozioni, attraverso la classe 'PromozioniService' */
	public ResponseEntity<?> deletePromozione(@PathVariable("idPromozione") Long idPromozione)
	{
		try {
			promozioniService.deletePromozione(idPromozione);
			return  new ResponseEntity<>(new Messaggio("PROMOZIONE RIMOSSA"),HttpStatus.OK);
		} catch (NotFoundException e) {
			// TODO: handle exception
			return  new ResponseEntity<>(new Messaggio(e.getMessage()),HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping(value = "/getPromozioniFiltrate/{categoriaMerceologica}")
	@PreAuthorize("hasRole('CLIENTE')")
	/** metodo che restituisce la lista di promozioni filtrate in base alla categoria merceologica inserita, attraverso la classe 'PromozioniService' */
	public ResponseEntity<?> getPromozioniFiltrate(@PathVariable("categoriaMerceologica") CategorieMerceologiche categoriaMerceologica) {
		// TODO Auto-generated method stub
		try {
			return new ResponseEntity<List<Promozione>>(promozioniService.getPromozioniFiltrate(categoriaMerceologica),HttpStatus.OK);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			return  new ResponseEntity<>(new Messaggio(e.getMessage()),HttpStatus.BAD_REQUEST);
		}
	}

	
	@GetMapping(value = "/getPromozioniCommerciante")
	@PreAuthorize("hasRole('COMMERCIANTE')")
	/** metodo che restituisce la lista di promozioni relative ad uno specifico commerciante, attraverso la classe 'PromozioniService' */
	public ResponseEntity<?> getPromozioniCommerciante(Authentication authentication) {
		// TODO Auto-generated method stub
		String emailCommerciante = authentication.getName();
		try {
			return new ResponseEntity<List<Promozione>>(promozioniService.getPromozioniCommerciante(emailCommerciante),HttpStatus.OK);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			return  new ResponseEntity<>(new Messaggio(e.getMessage()),HttpStatus.BAD_REQUEST);
		}
	}

	
	@GetMapping(value = "/getPromozioniNonScadute")
	@PreAuthorize("hasRole('CLIENTE')")
	/** metodo che restituisce solo le promozioni non scadute, quindi attuali, attraverso la classe 'PromozioniService' */
	public ResponseEntity<List<Promozione>> getPromozioniNonScadute() {
		// TODO Auto-generated method stub
		
		return new ResponseEntity<List<Promozione>>(promozioniService.getPromozioniNonScadute(),HttpStatus.OK);
	}
	

}
