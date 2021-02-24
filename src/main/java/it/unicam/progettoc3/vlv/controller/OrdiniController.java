package it.unicam.progettoc3.vlv.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import it.unicam.progettoc3.vlv.entity.dto.NuovoOrdine;
import it.unicam.progettoc3.vlv.entity.elementi.Ordine;
import it.unicam.progettoc3.vlv.entity.elementi.PuntoDiRitiro;
import it.unicam.progettoc3.vlv.entity.utenti.Cliente;
import it.unicam.progettoc3.vlv.entity.utenti.Commerciante;

import it.unicam.progettoc3.vlv.service.OrdiniService;
import it.unicam.progettoc3.vlv.utils.Messaggio;
import javassist.NotFoundException;


/**
 * Questa classe definisce tutti i metodi relativi
 * agli ordini e, attraverso la loro invocazione, delega la loro precisa esecuzione alla classe service
 * corrispondente.
 */
@RestController
@RequestMapping("/gestoreOrdini")
@CrossOrigin(origins = "http://localhost:4200")
public class OrdiniController {

	// collegamento alla classe service corrispondente
	@Autowired
	OrdiniService ordiniService;

	@PreAuthorize("hasRole('COMMERCIANTE')")
	@PostMapping(value= "/addOrdine")
	/** metodo per aggiungere un ordine alla lista di ordini, tramite la classe 'OrdiniService' */
	public ResponseEntity<?> addOrdine(@Valid @RequestBody NuovoOrdine ordine , BindingResult bindingResult ,  Authentication authentication) {
		// TODO Auto-generated method stub
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(new Messaggio("controlla campi") , HttpStatus.BAD_REQUEST);
		
		
		String emailCommerciante=authentication.getName();
		try {
			ordiniService.addOrdine(ordine,emailCommerciante);
			return  new ResponseEntity<>(new Messaggio("ORDINE AGGIUNTO"),HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			return  new ResponseEntity<>(new Messaggio(e.getMessage()),HttpStatus.BAD_REQUEST);
		}catch (NotFoundException e){
			return  new ResponseEntity<>(new Messaggio(e.getMessage()),HttpStatus.BAD_REQUEST);
		}
		
	}

	
	@PreAuthorize("hasRole('CLIENTE')")
	@GetMapping(value = "/getOrdini")
	//@RequestParam String emailCliente
	/** metodo per ricevere la lista di tutti gli ordini relativi ad un solo cliente, tramite la classe 'OrdiniService' */
	public ResponseEntity<?> getOrdiniCliente(Authentication authentication) {
		// TODO Auto-generated method stub
		 
		String emailCliente=authentication.getName();
		try {
			//return  ordiniService.getOrdiniCliente(idCliente);
			return new ResponseEntity<List<Ordine>>(ordiniService.getOrdiniCliente(emailCliente), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			
			return new ResponseEntity<>(new Messaggio(e.getMessage()), HttpStatus.BAD_REQUEST);
		}catch(NotFoundException e){
			return new ResponseEntity<>(new Messaggio(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
	}

	@PreAuthorize("hasRole('COMMERCIANTE')")
    @GetMapping(value = "/getOrdiniCommerciante")
    //@RequestParam String emailCliente
	/** metodo per ricevere la lista di tutti gli ordini relativi ad un solo commerciante, tramite la classe 'OrdiniService' */
    public ResponseEntity<?> getOrdiniCommerciante(Authentication authentication) {
        // TODO Auto-generated method stub

        String emailCommerciante=authentication.getName();
        try {
            //return  ordiniService.getOrdiniCliente(idCliente);
            return new ResponseEntity<List<Ordine>>(ordiniService.getOrdiniCommerciante(emailCommerciante), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            // TODO: handle exception

            //return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            return  new ResponseEntity<>(new Messaggio(e.getMessage()),HttpStatus.BAD_REQUEST);
        }catch(NotFoundException e){
            //return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            return  new ResponseEntity<>(new Messaggio(e.getMessage()),HttpStatus.BAD_REQUEST);
        }

    }
	
	
	@PreAuthorize("hasRole('CLIENTE')")
	@PutMapping(value = "/ritiraOrdine/{idOrdine}/{codiceRitiro}")
	/** metodo per stabilire che un preciso ordine sia stato ritirato mediante l'utilizzo del codice di ritiro, tramite la classe 'OrdiniService' */
	public ResponseEntity<?> ritiraOrdine(@PathVariable("idOrdine") Long idOrdine , @PathVariable("codiceRitiro")  String codiceRitiro ) {
		// TODO Auto-generated method stub
		
		try {
					ordiniService.ritiraOrdine(idOrdine , codiceRitiro);
					 return new ResponseEntity<>(new Messaggio("ORDINE RITIRATO"),HttpStatus.OK);
			
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			return new ResponseEntity<>(new Messaggio(e.getMessage()), HttpStatus.BAD_REQUEST);
		}catch(NotFoundException e){
			return new ResponseEntity<>(new Messaggio(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
	}

	
	@PreAuthorize("hasRole('CORRIERE')")
	@GetMapping(value = "/getOrdiniLiberi")
	/** metodo per ricevere la lista di tutti gli ordini con stato 'IN_ACCETTAZIONE', tramite la classe 'OrdiniService' */
	public ResponseEntity<List<Ordine>> getOrdiniLiberi() {
		// TODO Auto-generated method stub
		return new ResponseEntity<List<Ordine>>(ordiniService.getOrdiniLiberi(), HttpStatus.OK);

	}
	
	
	@PreAuthorize("hasRole('CORRIERE')")
	@GetMapping(value = "/getOrdiniDaRitirare")
	/** metodo per ricevere la lista di tutti gli ordini con stato 'IN_RITIRO' relativi a un solo corriere, tramite la classe 'OrdiniService' */
	public ResponseEntity<?> getOrdiniDaRitirare(Authentication authentication) {
		// TODO Auto-generated method stub
		
		
		String emailCorriere=authentication.getName();
		try {
			return new ResponseEntity<List<Ordine>>(ordiniService.getOrdiniDaRitirare(emailCorriere), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			return new ResponseEntity<>(new Messaggio(e.getMessage()), HttpStatus.BAD_REQUEST);
		}catch(NotFoundException e){
			return new ResponseEntity<>(new Messaggio(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	@PreAuthorize("hasRole('CORRIERE')")
	@GetMapping(value = "/getOrdiniInTransito")
	/** metodo per ricevere la lista di tutti gli ordini con stato 'IN_TRANSITO' relativi a un solo corriere, tramite la classe 'OrdiniService' */
	public ResponseEntity<?> getOrdiniInTransito(Authentication authentication) {
		// TODO Auto-generated method stub
		
		String emailCorriere=authentication.getName();
		try {
			return new ResponseEntity<List<Ordine>>(ordiniService.getOrdiniInTransito(emailCorriere), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			return new ResponseEntity<>(new Messaggio(e.getMessage()), HttpStatus.BAD_REQUEST);
		}catch(NotFoundException e){
			return new ResponseEntity<>(new Messaggio(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	
	@PreAuthorize("hasRole('CORRIERE')")
	@GetMapping(value = "/getOrdiniConsegnatiInPuntoDiRitiro")
	/** metodo per ricevere la lista di tutti gli ordini con stato 'PRONTO_PER_IL_RITIRO' relativi a un solo corriere, tramite la classe 'OrdiniService' */
	public ResponseEntity<?> getOrdiniConsegnatiInPuntoDiRitiro(Authentication authentication) {
		// TODO Auto-generated method stub
		
		String emailCorriere=authentication.getName();
		try {
			return new ResponseEntity<List<Ordine>>(ordiniService.getOrdiniConsegnatiInPuntoRitiro(emailCorriere), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			return new ResponseEntity<>(new Messaggio(e.getMessage()), HttpStatus.BAD_REQUEST);
		}catch(NotFoundException e){
			return new ResponseEntity<>(new Messaggio(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PreAuthorize("hasRole('CORRIERE')")
	@GetMapping(value = "/getOrdiniConsegnati")
	/** metodo per ricevere la lista di tutti gli ordini con stato 'CONSEGNATO' relativi a un solo corriere, tramite la classe 'OrdiniService' */
	public ResponseEntity<?> getOrdiniConsegnati(Authentication authentication) {
		// TODO Auto-generated method stub
		
		String emailCorriere=authentication.getName();
		try {
			return new ResponseEntity<List<Ordine>>(ordiniService.getOrdiniConsegnati(emailCorriere), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			return new ResponseEntity<>(new Messaggio(e.getMessage()), HttpStatus.BAD_REQUEST);
		}catch(NotFoundException e){
			return new ResponseEntity<>(new Messaggio(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PreAuthorize("hasRole('CORRIERE')")
	@PutMapping(value = "/setPresaInCaricoOrdine/{idOrdine}/{dataPrevistaRitiro}")
	/**
	 *  metodo per assegnare la presa in carico di uno specifico ordine ad uno specifico corriere, viene
	 *  stabilita anche la data prevista per il ritiro dell'ordine dal commerciante e
	 *  cambiato lo stato dell'ordine in 'IN_RITIRO', tutto tramite la classe 'OrdiniService'
	 */
	public ResponseEntity<?> setPresaInCaricoOrdine(@PathVariable("idOrdine") Long idOrdine,@PathVariable("dataPrevistaRitiro")   String  dataPrevistaRitiro ,   Authentication authentication) {
		
	// TODO Auto-generated method stub
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String emailCorriere = authentication.getName();
		
		try {
			//trasforma la stringa fornita in data relativa
			Date data = format.parse(dataPrevistaRitiro);
			
			ordiniService.setPresaInCaricoOrdine(idOrdine,  data,emailCorriere);
			return new ResponseEntity<>(new Messaggio("ORDINE PRESO IN CARICO"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			return new ResponseEntity<>(new Messaggio(e.getMessage()), HttpStatus.BAD_REQUEST);
		}catch(NotFoundException e){
			return new ResponseEntity<>(new Messaggio(e.getMessage()), HttpStatus.BAD_REQUEST);
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		return new ResponseEntity<>(new Messaggio("CONTROLLARE DATA"), HttpStatus.BAD_REQUEST);
		}
	}

	
	@PreAuthorize("hasRole('CORRIERE')")
	@PutMapping(value = "/setDataConsegnaPrevista/{idOrdine}/{dataConsegnaPrevista}")
	
	/**
	 *  metodo per stabilire la data di consegna prevista relativa ad un ordine e per cambiare lo stato 
	 *  dell'ordine in 'IN_TRANSITO', tramite la classe 'OrdiniService'
	 */
	public ResponseEntity<?> setDataConsegnaPrevista(@PathVariable("idOrdine") Long idOrdine,@PathVariable("dataConsegnaPrevista")   String dataConsegnaPrevista ) {
		// TODO Auto-generated method stub
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			// trasforma la stringa fornita in data relativa
			Date data = format.parse(dataConsegnaPrevista);
			ordiniService.setDataConsegnaPrevista(idOrdine, data);
			return new ResponseEntity<>(new Messaggio("DATA CONSEGNA PREVISTA IMPOSTATA"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
		// TODO: handle exception
		return new ResponseEntity<>(new Messaggio(e.getMessage()), HttpStatus.BAD_REQUEST);
		}catch(NotFoundException e){
			return new ResponseEntity<>(new Messaggio(e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(new Messaggio("CONTROLLARE DATA"), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	@PreAuthorize("hasRole('CORRIERE')")
	@PutMapping(value = "/setOrdineConsegnato/{idOrdine}")
	/**
	 *  metodo per stabilire che un preciso ordine e' stato consegnato presso un domicilio, attraverso 
	 *  il cambio del suo stato in 'CONSEGNATO' , tramite la classe 'OrdiniService'
	 */
	public ResponseEntity<?> setOrdineConsegnato(@PathVariable("idOrdine") Long idOrdine ) {
		// TODO Auto-generated method stub
		
		try {
			ordiniService.setOrdineConsegnato(idOrdine);
			return new ResponseEntity<>(new Messaggio("ORDINE CONSEGNATO"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			return new ResponseEntity<>(new Messaggio(e.getMessage()), HttpStatus.BAD_REQUEST);
		}catch(NotFoundException e){
			return new ResponseEntity<>(new Messaggio(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
	}

	
	@PreAuthorize("hasRole('CORRIERE')")
	@PutMapping(value = "/setOrdineProntoPerIlRitiro/{idOrdine}")
	/**
	 *  metodo per stabilire che un preciso ordine e' stato consegnato presso un punto di ritiro diverso
	 *  dal domicilio di un cliente, attraverso il cambio dello stato dell'ordine in 'PRONTO_PER_IL_RITIRO',
	 *  tutto tramite la classe 'OrdiniService'
	 */
	public ResponseEntity<?> setOrdineProntoPerIlRitiro(@PathVariable("idOrdine") Long idOrdine) {
		// TODO Auto-generated method stub
		
		try {
			ordiniService.setOrdineProntoPerIlRitiro(idOrdine);
			 		
			return new ResponseEntity<>(new Messaggio("ORDINE DEPOSITATO PRESSO PUNTO DI RITIRO"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			return new ResponseEntity<>(new Messaggio(e.getMessage()), HttpStatus.BAD_REQUEST);
		}catch(NotFoundException e){
			return new ResponseEntity<>(new Messaggio(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	//@PreAuthorize("hasRole('CORRIERE')")
	@GetMapping(value = "/getCommercianteById/{idCommerciante}")
	/** metodo per ricercare un commerciante attraverso il suo id ed eventualmente restituirlo in maniera dettagliata */
	public ResponseEntity<?> getCommercianteById(@PathVariable("idCommerciante")Long idCommerciante)
	{
		try {
			return new ResponseEntity<Commerciante>(ordiniService.getCommercianteById(idCommerciante), HttpStatus.OK);
		} catch (NotFoundException e) {
			// TODO: handle exception
			return new ResponseEntity<>(new Messaggio(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	//@PreAuthorize("hasRole('CORRIERE')")
	@GetMapping(value = "/getPuntoDiRitiroById/{idPuntoDiRitiro}")
	/** metodo per ricercare un punto di ritiro attraverso il suo id ed eventualmente restituirlo in maniera dettagliata */
	public ResponseEntity<?> getPuntoDiRitiroById(@PathVariable("idPuntoDiRitiro")Long idPuntoDiRitiro)
	{
		try {
			return  new ResponseEntity<PuntoDiRitiro>(ordiniService.getPuntoDiRitiroById(idPuntoDiRitiro),HttpStatus.OK);	 
		} catch (NotFoundException e) {
			// TODO: handle exception
			return new ResponseEntity<>(new Messaggio(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	//@PreAuthorize("hasRole('CORRIERE')")
	@GetMapping(value = "/getClienteById/{idCliente}")
	/** metodo per ricercare un cliente attraverso il suo id ed eventualmente restituirlo in maniera dettagliata */
	public ResponseEntity<?> getClienteById(@PathVariable("idCliente")Long idCliente)
	{
		try {
			return  new ResponseEntity<Cliente>(ordiniService.getClienteById(idCliente),HttpStatus.OK); 
		} catch (NotFoundException e) {
			// TODO: handle exception
			return new ResponseEntity<>(new Messaggio(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	
}