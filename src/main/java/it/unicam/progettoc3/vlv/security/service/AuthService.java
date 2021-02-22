package it.unicam.progettoc3.vlv.security.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unicam.progettoc3.vlv.entity.dto.NuovoCliente;
import it.unicam.progettoc3.vlv.entity.dto.NuovoCommerciante;
import it.unicam.progettoc3.vlv.entity.dto.NuovoCorriere;
import it.unicam.progettoc3.vlv.entity.enumeratori.NomiRuoli;
import it.unicam.progettoc3.vlv.entity.utenti.Amministratore;
import it.unicam.progettoc3.vlv.entity.utenti.Cliente;
import it.unicam.progettoc3.vlv.entity.utenti.Commerciante;
import it.unicam.progettoc3.vlv.entity.utenti.Corriere;
import it.unicam.progettoc3.vlv.entity.utenti.Utente;
import it.unicam.progettoc3.vlv.repository.AmministratoreRepository;
import it.unicam.progettoc3.vlv.security.dto.JwtDto;
import it.unicam.progettoc3.vlv.security.dto.LoginUtente;
import it.unicam.progettoc3.vlv.security.jtw.JwtProvider;
import it.unicam.progettoc3.vlv.service.AmministratoreService;
import it.unicam.progettoc3.vlv.service.ClienteService;
import it.unicam.progettoc3.vlv.service.CommercianteService;
import it.unicam.progettoc3.vlv.service.CorriereService;
import it.unicam.progettoc3.vlv.service.UtenteService;
import it.unicam.progettoc3.vlv.utils.Messaggio;

@Service
@Transactional
/** classe service relativa alla classe AuthController, esegue i metodi delegati dal controller */
public class AuthService {

		/** servizio per codificare le password */
		@Autowired
		PasswordEncoder passwordEncoder;
		
		@Autowired
		/** servizio per avanzare una richiesta di autenticazione */
		AuthenticationManager authenticationManager;
		
		// collegamenti alle varie classi Service necessarie
		@Autowired
		ClienteService clienteService;
		
		@Autowired
		UtenteService utenteService;;
		
		@Autowired
		CommercianteService commercianteService;
		
		@Autowired
		CorriereService corriereService;
		
		@Autowired
		AmministratoreService amministratoreService;
		
		// collegamento alla repository amministratore
		@Autowired
		AmministratoreRepository amministratoreRepository;
		
		// collegamento al jwtProvider, per generare il token
		@Autowired
		JwtProvider jwtProvider;
		
		
		/** Registrazione nuovo cliente, se l'email fornita esiste gia', stampa errore */
		public ResponseEntity<?> nuovoCliente(NuovoCliente nuovoCliente)
		{
			if(utenteService.existsByEmail(nuovoCliente.getEmail()))
			return new ResponseEntity<>(new Messaggio("EMAIL GIA UTILIZZATA"),HttpStatus.BAD_REQUEST);
			Utente utente = new Utente(nuovoCliente.getEmail(), passwordEncoder.encode(nuovoCliente.getPassword()),NomiRuoli.ROLE_CLIENTE);
			Cliente cliente = new Cliente(nuovoCliente.getNome(), nuovoCliente.getCognome(), nuovoCliente.getIndirizzo());
			cliente.setUtente(utente);
			utente.setAssociato(cliente);
			 
			clienteService.save(cliente);
			utenteService.save(utente);
			return new ResponseEntity<>(new Messaggio("Cliente AGGIUNTO"),HttpStatus.CREATED);
		}
		
		
		/** Registrazione nuovo commerciante, se l'email fornita esiste gia', stampa errore */
		public ResponseEntity<?> nuovoCommerciante(NuovoCommerciante nuovoCommerciante)
		{
			
			if(utenteService.existsByEmail(nuovoCommerciante.getEmail()))
			return new ResponseEntity<>(new Messaggio("EMAIL GIA UTILIZZATA"),HttpStatus.BAD_REQUEST);
			Utente utente = new Utente(nuovoCommerciante.getEmail(), passwordEncoder.encode(nuovoCommerciante.getPassword()),NomiRuoli.ROLE_COMMERCIANTE);
			Commerciante commerciante = new Commerciante(nuovoCommerciante.getCategoriaMerceologica(), nuovoCommerciante.getIndirizzoPuntoVendita(), nuovoCommerciante.getNomePuntoVendita());
			commerciante.setUtente(utente);
			utente.setAssociato(commerciante);
			 
			commercianteService.save(commerciante);
			utenteService.save(utente);
			return new ResponseEntity<>(new Messaggio("Commerciante AGGIUNTO"),HttpStatus.CREATED);
		}
		
		
		/** Registrazione nuovo corriere, se l'email fornita esiste gia', stampa errore */
		public ResponseEntity<?> nuovoCorriere(NuovoCorriere nuovoCorriere)
		{
			
			if(utenteService.existsByEmail(nuovoCorriere.getEmail()))
				return new ResponseEntity<>(new Messaggio("EMAIL GIA UTILIZZATA"),HttpStatus.BAD_REQUEST);
			Utente utente = new Utente(nuovoCorriere.getEmail(), passwordEncoder.encode(nuovoCorriere.getPassword()),NomiRuoli.ROLE_CORRIERE);
			Corriere corriere = new Corriere(nuovoCorriere.getNomeDitta());
			corriere.setUtente(utente);
			utente.setAssociato(corriere);
			 
			corriereService.save(corriere);
			utenteService.save(utente);
			return new ResponseEntity<>(new Messaggio("Corriere AGGIUNTO"),HttpStatus.CREATED);
		}
		
		
		/** Login di un cliente, l'authenticationManager avanza la richiesta di autenticazione e il jwtProvider genera un token */
		public ResponseEntity<JwtDto> login(LoginUtente loginUtente)
		{
			
			Authentication authentication =
					authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUtente.getEmail(), loginUtente.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtProvider.generateToken(authentication);
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities()) ;
			
			return new ResponseEntity<>(jwtDto ,HttpStatus.OK);

		}

		/** Registrazione nuovo amministratore, se l'email fornita esiste gia', non esegue nulla */
		public ResponseEntity<?> nuovoAmministratore(String email, String password) {
			// TODO Auto-generated method stub
			if(amministratoreService.existAdmin())
				return new ResponseEntity<>(new Messaggio("ESISTE GIA' UN AMMINISTRATORE"),HttpStatus.BAD_REQUEST);

			Utente utente = new Utente(email, passwordEncoder.encode(password),NomiRuoli.ROLE_AMMINISTRATORE);
			Amministratore amministratore = new Amministratore();
			amministratore.setUtente(utente);
			utente.setAssociato(amministratore);
				 
			utenteService.save(utente);
			amministratoreRepository.save(amministratore);
			return new ResponseEntity<>(new Messaggio("Amministratore AGGIUNTO"),HttpStatus.CREATED);
		}
				
		
}
