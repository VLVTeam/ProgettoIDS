package it.unicam.progettoc3.vlv.security.controller;



import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.progettoc3.vlv.entity.dto.NuovoCliente;
import it.unicam.progettoc3.vlv.entity.dto.NuovoCommerciante;
import it.unicam.progettoc3.vlv.entity.dto.NuovoCorriere;
import it.unicam.progettoc3.vlv.security.dto.JwtDto;
import it.unicam.progettoc3.vlv.security.dto.LoginUtente;
import it.unicam.progettoc3.vlv.security.service.AuthService;
import it.unicam.progettoc3.vlv.utils.Messaggio;



@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {


		
		@Autowired
		AuthService authService;
		
		@PostMapping("/nuovoCliente")
		public ResponseEntity<?> nuovoCliente(@Valid @RequestBody NuovoCliente nuovoCliente , BindingResult bindingResult)
		{
			if(bindingResult.hasErrors())
				//return new ResponseEntity<String>("controlla campi" , HttpStatus.BAD_REQUEST);
			return new ResponseEntity<>(new Messaggio("controlla campi"),HttpStatus.BAD_REQUEST);
			return authService.nuovoCliente(nuovoCliente);
		}
		
		@PostMapping("/nuovoCommerciante")
		public ResponseEntity<?> nuovoCommerciante(@Valid @RequestBody NuovoCommerciante nuovoCommerciante , BindingResult bindingResult)
		{
			if(bindingResult.hasErrors())
				return new ResponseEntity<>(new Messaggio("controlla campi"),HttpStatus.BAD_REQUEST);
			//	return new ResponseEntity<String>("controlla campi" , HttpStatus.BAD_REQUEST);
			return authService.nuovoCommerciante(nuovoCommerciante);
		}
		
		@PostMapping("/nuovoCorriere")
		public ResponseEntity<?> nuovoCorriere(@Valid @RequestBody NuovoCorriere nuovoCorriere , BindingResult bindingResult)
		{
			if(bindingResult.hasErrors())
				return new ResponseEntity<>(new Messaggio("controlla campi"),HttpStatus.BAD_REQUEST);
				//return new ResponseEntity<String>("controlla campi" , HttpStatus.BAD_REQUEST);
			return authService.nuovoCorriere(nuovoCorriere);
		}
		
		
		
		
		
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@PostMapping("/login")
		public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUtente loginUtente , BindingResult bindingResult)
		//public ResponseEntity<?> login(@Valid @RequestBody LoginUtente loginUtente , BindingResult bindingResult)
		{
			if(bindingResult.hasErrors())
				//return new ResponseEntity<String>("controlla campi" , HttpStatus.BAD_REQUEST);
				return new ResponseEntity(new Messaggio("controlla campi") , HttpStatus.BAD_REQUEST);

			return authService.login(loginUtente);

		}
		
}