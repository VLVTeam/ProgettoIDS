package it.unicam.progettoc3.vlv.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unicam.progettoc3.vlv.entity.utenti.Utente;
import it.unicam.progettoc3.vlv.repository.UtenteRepository;

@Service
@Transactional
/**
 * La classe UtenteService definisce i metodi per cercare un utente partendo da una email, per cercare la sua esistenza partendo da una email oppure
 * per salvare un utente nella repository utente
 */
public class UtenteService {

	
	@Autowired
	UtenteRepository utenteRepository;
	
	public Optional<Utente> getByEmail (String email)
	{
		return utenteRepository.findByEmail(email);
	}
	
	public boolean existsByEmail(String email)
	{
		return utenteRepository.existsByEmail(email);
	}
	
	public void save(Utente utente)
	{
		utenteRepository.save(utente);
	}
	
	
}
