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
import javassist.NotFoundException;


@RestController
@RequestMapping("/gestoreOrdini")
@CrossOrigin(origins = "http://localhost:8080")
public class OrdiniController {

	
	@Autowired
	OrdiniService ordiniService;

	@PreAuthorize("hasRole('COMMERCIANTE')")
	@PostMapping(value= "/addOrdine")
	
	public ResponseEntity<String> addOrdine(@Valid @RequestBody NuovoOrdine ordine , BindingResult bindingResult ,  Authentication authentication) {
		// TODO Auto-generated method stub
		if(bindingResult.hasErrors())
			return new ResponseEntity<String>("controlla campi" , HttpStatus.BAD_REQUEST);
		
		
		String emailCommerciante=authentication.getName();
		try {
			ordiniService.addOrdine(ordine,emailCommerciante);
			return  new ResponseEntity<>("ORDINE AGGIUNTO",HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			return  new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}catch (NotFoundException e){
			return  new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
	}

	
	
	@PreAuthorize("hasRole('CLIENTE')")
	@GetMapping(value = "/getOrdini")
	//@RequestParam String emailCliente
	public ResponseEntity<?> getOrdiniCliente(Authentication authentication) {
		// TODO Auto-generated method stub
		 
		String emailCliente=authentication.getName();
		try {
			//return  ordiniService.getOrdiniCliente(idCliente);
			return new ResponseEntity<List<Ordine>>(ordiniService.getOrdiniCliente(emailCliente), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}catch(NotFoundException e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}

	
	@PreAuthorize("hasRole('CLIENTE')")
	@PutMapping(value = "/ritiraOrdine/{idOrdine}/{codiceRitiro}")
	public ResponseEntity<String> ritiraOrdine(@PathVariable("idOrdine") Long idOrdine , @PathVariable("codiceRitiro")  String codiceRitiro ) {
		// TODO Auto-generated method stub
		
		try {
			
			 
					ordiniService.ritiraOrdine(idOrdine , codiceRitiro);
					 return new ResponseEntity<>("ORDINE RITIRATO",HttpStatus.OK);
			
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}catch(NotFoundException e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}

	@PreAuthorize("hasRole('CORRIERE')")
	@GetMapping(value = "/getOrdiniLiberi")
	
	public ResponseEntity<List<Ordine>> getOrdiniLiberi() {
		// TODO Auto-generated method stub
		return new ResponseEntity<List<Ordine>>(ordiniService.getOrdiniLiberi(), HttpStatus.OK);

	}
	
	@PreAuthorize("hasRole('CORRIERE')")
	@GetMapping(value = "/getOrdiniDaRitirare")
	
	public ResponseEntity<?> getOrdiniDaRitirare( Authentication authentication) {
		// TODO Auto-generated method stub
		
		
		String emailCorriere=authentication.getName();
		try {
			return new ResponseEntity<List<Ordine>>(ordiniService.getOrdiniDaRitirare(emailCorriere), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}catch(NotFoundException e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PreAuthorize("hasRole('CORRIERE')")
	@GetMapping(value = "/getOrdiniInTransito")
	
	public ResponseEntity<?> getOrdiniInTransito( Authentication authentication) {
		// TODO Auto-generated method stub
		
		String emailCorriere=authentication.getName();
		try {
			return new ResponseEntity<List<Ordine>>(ordiniService.getOrdiniInTransito(emailCorriere), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}catch(NotFoundException e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('CORRIERE')")
	@PutMapping(value = "/setPresaInCaricoOrdine/{idOrdine}/{dataPrevistaRitiro}")
	public ResponseEntity<String> setPresaInCaricoOrdine(@PathVariable("idOrdine") Long idOrdine,@PathVariable("dataPrevistaRitiro")  String dataPrevistaRitiro ,   Authentication authentication) {
		
	// TODO Auto-generated method stub
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String emailCorriere = authentication.getName();
		try {
			Date data = format.parse(dataPrevistaRitiro);
					 ordiniService.setPresaInCaricoOrdine(idOrdine,  data,emailCorriere);
					 return new ResponseEntity<>("ORDINE PRESO IN CARICO", HttpStatus.BAD_REQUEST);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}catch(NotFoundException e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>("CONTROLLARE DATA", HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('CORRIERE')")
	@PutMapping(value = "/setDataConsegnaPrevista/{idOrdine}/{dataConsegnaPrevista}")
	
	public ResponseEntity<String> setDataConsegnaPrevista(@PathVariable("idOrdine") Long idOrdine,@PathVariable("dataConsegnaPrevista")   String dataConsegnaPrevista ) {
		// TODO Auto-generated method stub
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			Date data = format.parse(dataConsegnaPrevista);
			 ordiniService.setDataConsegnaPrevista(idOrdine, data);
			 return new ResponseEntity<>("DATA CONSEGNA PREVISTA IMPOSTATA", HttpStatus.BAD_REQUEST);
} catch (IllegalArgumentException e) {
	// TODO: handle exception
	return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
}catch(NotFoundException e){
	return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
} catch (ParseException e) {
	// TODO Auto-generated catch block
	return new ResponseEntity<>("CONTROLLARE DATA", HttpStatus.BAD_REQUEST);
}
	}
	
	
	@PreAuthorize("hasRole('CORRIERE')")
	@PutMapping(value = "/setOrdineConsegnato/{idOrdine}")
	
	public ResponseEntity<String> setOrdineConsegnato(@PathVariable("idOrdine") Long idOrdine ) {
		// TODO Auto-generated method stub
		
		try {
			 ordiniService.setOrdineConsegnato(idOrdine);
			
			 return new ResponseEntity<>("ORDINE CONSEGNATO", HttpStatus.BAD_REQUEST);
} catch (IllegalArgumentException e) {
	// TODO: handle exception
	return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
}catch(NotFoundException e){
	return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
}
		
	}

	@PreAuthorize("hasRole('CORRIERE')")
	@PutMapping(value = "/setOrdineProntoPerIlRitiro/{idOrdine}")
	
	public ResponseEntity<String> setOrdineProntoPerIlRitiro(@PathVariable("idOrdine") Long idOrdine) {
		// TODO Auto-generated method stub
		
		try {
			
			 ordiniService.setOrdineProntoPerIlRitiro(idOrdine);
			 		
			 return new ResponseEntity<>("ORDINE DEPOSITATO PRESSO PUNTO DI RITIRO", HttpStatus.BAD_REQUEST);
} catch (IllegalArgumentException e) {
	// TODO: handle exception
	return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
}catch(NotFoundException e){
	return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
}
		
	}
	
	@PreAuthorize("hasRole('CORRIERE')")
	@GetMapping(value = "/getCommercianteById/{idCommerciante}")
	public ResponseEntity<?> getCommercianteById(@PathVariable("idCommerciante")Long idCommerciante)
	{
		try {
			return new ResponseEntity<Commerciante>(ordiniService.getCommercianteById(idCommerciante), HttpStatus.OK);
	 		
			 
		} catch (NotFoundException e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PreAuthorize("hasRole('CORRIERE')")
	@GetMapping(value = "/getPuntoDiRitiroById/{idPuntoDiRitiro}")
	public ResponseEntity<?> getPuntoDiRitiroById(@PathVariable("idPuntoDiRitiro")Long idPuntoDiRitiro)
	{
		try {
			return  new ResponseEntity<PuntoDiRitiro>(ordiniService.getPuntoDiRitiroById(idPuntoDiRitiro),HttpStatus.OK);
	 		
			 
		} catch (NotFoundException e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PreAuthorize("hasRole('CORRIERE')")
	@GetMapping(value = "/getClienteById/{idCliente}")
	public ResponseEntity<?> getClienteById(@PathVariable("idCliente")Long idCliente)
	{
		try {
			return  new ResponseEntity<Cliente>(ordiniService.getClienteById(idCliente),HttpStatus.OK);
	 		
			 
		} catch (NotFoundException e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
