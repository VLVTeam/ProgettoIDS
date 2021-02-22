package it.unicam.progettoc3.vlv.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unicam.progettoc3.vlv.entity.enumeratori.NomiRuoli;
import it.unicam.progettoc3.vlv.entity.utenti.Commerciante;
import it.unicam.progettoc3.vlv.entity.utenti.Utente;
import it.unicam.progettoc3.vlv.repository.CommercianteRepository;
import it.unicam.progettoc3.vlv.repository.UtenteRepository;
import javassist.NotFoundException;



@Service
@Transactional
/**
 * La classe CommercianteService definisce precisamente il funzionamento
 * dei metodi presenti anche nella classe controller, fornendoli appunto alla classe CommercianteController.
 */
public class CommercianteService {

	// collegamenti alle repository
	@Autowired
	CommercianteRepository commercianteRepository;
	
	@Autowired
	UtenteRepository utenteRepository;
	
	/** metodo per salvare un commerciante nella repository */
	public void save(Commerciante commerciante)
	{
		commercianteRepository.save(commerciante);
	}
	
	
	public void accettaIscrizioneCommerciante(Long idCommerciante) throws NotFoundException{
		// TODO Auto-generated method stub
		// se trova il commerciante attraverso l'id fornito, imposta lo stato della sua iscrizione a true, poi salva
		Commerciante commerciante = commercianteRepository.findById(idCommerciante).orElseThrow(() -> new NotFoundException("COMMERCIANTE NON TROVATO"));
		Utente utente = commerciante.getUtente();
		if(utente == null) throw new NotFoundException("UTENTE NON TROVATO");
		utente.setStato(true);
		utenteRepository.save(utente);
	}
	
	
	public List<Utente> getCommerciantiDaAccettare() {
		// TODO Auto-generated method stub
		// crea una lista di utenti in cui inserisce tutti gli utenti
		List<Utente> commercianti =utenteRepository.findAll();
		/*
		 * ne crea un'altra in cui a mano a mano aggiunge solo gli utenti presenti nella prima lista creata, che hanno uno stato iscrizione 'false'
		 * e un ruolo corrispondente al commerciante
		 */
		List<Utente> commerciantiDaAccettare = new ArrayList<>();
		commercianti.forEach(Utente ->{
			if(Utente.getNomeRuolo()==NomiRuoli.ROLE_COMMERCIANTE && !Utente.isStato()) commerciantiDaAccettare.add(Utente);
		});
		// restituisce la lista risultante, puo' essere null
		if (commerciantiDaAccettare.isEmpty()) return null;
		return commerciantiDaAccettare;
	}
	
	
	public Commerciante getCommercianteByIdUtente(Long idUtente) throws IllegalArgumentException,NotFoundException{
		// TODO Auto-generated method stub
		Utente utente = utenteRepository.findById(idUtente).orElseThrow(() -> new NotFoundException("Utente NON TROVATO"));
		if(utente.getNomeRuolo()!= NomiRuoli.ROLE_COMMERCIANTE) throw new IllegalArgumentException("UTENTE NON E UN CORRIERE");

		Commerciante commerciante = (Commerciante) utente.getAssociato();
		if(commerciante == null) throw new NotFoundException("COMMERCIANTE NON TROVATO");
		return commerciante;
	}


	public List<Commerciante> getCommercianti() {
		// TODO Auto-generated method stub
		// crea una lista, la riempie con tutti i commercianti e la restituisce, puo' essere null
		Iterable<Commerciante> iteratore =commercianteRepository.findAll();
		List<Commerciante> commercianti = new ArrayList<>();
		iteratore.forEach(Commerciante ->{
			 commercianti.add(Commerciante);
		});
		if(commercianti.isEmpty()) return null;
		return commercianti;
	}
	

	
}