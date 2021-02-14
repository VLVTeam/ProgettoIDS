package it.unicam.progettoc3.vlv.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.unicam.progettoc3.vlv.entity.utenti.Utente;
import it.unicam.progettoc3.vlv.security.entity.UtentePrincipale;
import it.unicam.progettoc3.vlv.service.UtenteService;

@Service
public class UserDetailsServiceImpl   implements UserDetailsService{

	@Autowired
	UtenteService utenteService;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Utente utente =  utenteService.getByEmail(email).get();
		
		return UtentePrincipale.build(utente);
	}

}
