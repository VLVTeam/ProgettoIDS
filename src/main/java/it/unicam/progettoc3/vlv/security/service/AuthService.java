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
import it.unicam.progettoc3.vlv.service.ClienteService;
import it.unicam.progettoc3.vlv.service.CommercianteService;
import it.unicam.progettoc3.vlv.service.CorriereService;
import it.unicam.progettoc3.vlv.service.UtenteService;
import it.unicam.progettoc3.vlv.utils.Messaggio;

@Service
@Transactional
public class AuthService {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
		
		@Autowired
		ClienteService clienteService;
		
		@Autowired
		UtenteService utenteService;;
		
		@Autowired
		CommercianteService commercianteService;
		
		@Autowired
		CorriereService corriereService;
		
		@Autowired
		AmministratoreRepository amministratoreRepository;
		
		@Autowired
		JwtProvider jwtProvider;
		
		
		
		public ResponseEntity<?> nuovoCliente(  NuovoCliente nuovoCliente  )
		{
			
			if(utenteService.existsByEmail(nuovoCliente.getEmail()))
			//	return new ResponseEntity<String>("EMAIL GIA UTILIZZATA ",HttpStatus.BAD_REQUEST);
			return new ResponseEntity<>(new Messaggio("EMAIL GIA UTILIZZATA"),HttpStatus.BAD_REQUEST);
			Utente utente = new Utente(nuovoCliente.getEmail(), passwordEncoder.encode(nuovoCliente.getPassword()),NomiRuoli.ROLE_CLIENTE);
			 Cliente cliente = new Cliente(nuovoCliente.getNome(), nuovoCliente.getCognome(), nuovoCliente.getIndirizzo());
			 cliente.setUtente(utente);
			 utente.setAssociato(cliente);
			 
			//Set<Ruolo> ruoli = new HashSet<>();
			//ruoli.add(ruoloService.getByNomeRuolo(NomiRuoli.ROLE_USER).get());
			//if(nuovoUtente.getRuoli().contains("AMMINISTRATORE"))
			//	ruoli.add(ruoloService.getByNomeRuolo(NomiRuoli.ROLE_AMMINISTRATORE).get());
			//utente.setRuoli(ruoli);
			 
			clienteService.save(cliente);
			utenteService.save(utente);
			//return new ResponseEntity<String>("UTENTE AGGIUNTO", HttpStatus.CREATED);
			return new ResponseEntity<>(new Messaggio("Cliente AGGIUNTO"),HttpStatus.CREATED);
		}
		
		
		public ResponseEntity<?> nuovoCommerciante( NuovoCommerciante nuovoCommerciante )
		{
			
			if(utenteService.existsByEmail(nuovoCommerciante.getEmail()))
			//	return new ResponseEntity<String>("EMAIL GIA UTILIZZATA ",HttpStatus.BAD_REQUEST);
			return new ResponseEntity<>(new Messaggio("EMAIL GIA UTILIZZATA"),HttpStatus.BAD_REQUEST);
			Utente utente = new Utente(nuovoCommerciante.getEmail(), passwordEncoder.encode(nuovoCommerciante.getPassword()),NomiRuoli.ROLE_COMMERCIANTE);
			 Commerciante commerciante = new Commerciante(nuovoCommerciante.getCategoriaMerceologica(), nuovoCommerciante.getIndirizzoPuntoVendita(), nuovoCommerciante.getNomePuntoVendita());
			 commerciante.setUtente(utente);
			 utente.setAssociato(commerciante);
			 
			//Set<Ruolo> ruoli = new HashSet<>();
			//ruoli.add(ruoloService.getByNomeRuolo(NomiRuoli.ROLE_USER).get());
			//if(nuovoUtente.getRuoli().contains("AMMINISTRATORE"))
			//	ruoli.add(ruoloService.getByNomeRuolo(NomiRuoli.ROLE_AMMINISTRATORE).get());
			//utente.setRuoli(ruoli);
			 
			commercianteService.save(commerciante);
			utenteService.save(utente);
			//return new ResponseEntity<String>("UTENTE AGGIUNTO", HttpStatus.CREATED);
			return new ResponseEntity<>(new Messaggio("Commerciante AGGIUNTO"),HttpStatus.CREATED);
		}
		
		
		public ResponseEntity<?> nuovoCorriere(  NuovoCorriere nuovoCorriere )
		{
			
			if(utenteService.existsByEmail(nuovoCorriere.getEmail()))
			//	return new ResponseEntity<String>("EMAIL GIA UTILIZZATA ",HttpStatus.BAD_REQUEST);
			return new ResponseEntity<>(new Messaggio("EMAIL GIA UTILIZZATA"),HttpStatus.BAD_REQUEST);
			Utente utente = new Utente(nuovoCorriere.getEmail(), passwordEncoder.encode(nuovoCorriere.getPassword()),NomiRuoli.ROLE_CORRIERE);
			Corriere corriere = new Corriere(nuovoCorriere.getNomeDitta());
			 corriere.setUtente(utente);
			 utente.setAssociato(corriere);
			
			//Set<Ruolo> ruoli = new HashSet<>();
			//ruoli.add(ruoloService.getByNomeRuolo(NomiRuoli.ROLE_USER).get());
			//if(nuovoUtente.getRuoli().contains("AMMINISTRATORE"))
			//	ruoli.add(ruoloService.getByNomeRuolo(NomiRuoli.ROLE_AMMINISTRATORE).get());
			//utente.setRuoli(ruoli);
			 
			corriereService.save(corriere);
			utenteService.save(utente);
			//return new ResponseEntity<String>("UTENTE AGGIUNTO", HttpStatus.CREATED);
			return new ResponseEntity<>(new Messaggio("Corriere AGGIUNTO"),HttpStatus.CREATED);
		}
		
		
		public ResponseEntity<JwtDto> login(  LoginUtente loginUtente )
		//public ResponseEntity<?> login(@Valid @RequestBody LoginUtente loginUtente , BindingResult bindingResult)
		{
			
			Authentication authentication =
					authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUtente.getEmail(), loginUtente.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtProvider.generateToken(authentication);
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities()) ;
			
			//return new ResponseEntity<JwtDto>(jwtDto ,HttpStatus.OK);
			return new ResponseEntity<>(jwtDto ,HttpStatus.OK);

		}


		public void nuovoAmministratore(String email, String password) {
			// TODO Auto-generated method stub
			if(!utenteService.existsByEmail(email)){
				
				Utente utente = new Utente(email, passwordEncoder.encode(password),NomiRuoli.ROLE_AMMINISTRATORE);
				Amministratore amministratore = new Amministratore();
				 amministratore.setUtente(utente);
				 utente.setAssociato(amministratore);
				
				//Set<Ruolo> ruoli = new HashSet<>();
				//ruoli.add(ruoloService.getByNomeRuolo(NomiRuoli.ROLE_USER).get());
				//if(nuovoUtente.getRuoli().contains("AMMINISTRATORE"))
				//	ruoli.add(ruoloService.getByNomeRuolo(NomiRuoli.ROLE_AMMINISTRATORE).get());
				//utente.setRuoli(ruoli);
				 
				 utenteService.save(utente);
				amministratoreRepository.save(amministratore);
			}
				//return new ResponseEntity<String>("UTENTE AGGIUNTO", HttpStatus.CREATED);
				
		}
}
