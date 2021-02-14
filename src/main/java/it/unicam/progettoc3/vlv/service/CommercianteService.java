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
public class CommercianteService {

	
	@Autowired
	CommercianteRepository commercianteRepository;
	
	@Autowired
	UtenteRepository utenteRepository;
	
	public void save(Commerciante commerciante)
	{
		commercianteRepository.save(commerciante);
	}
	
	
	public void accettaIscrizioneCommerciante(Long idCommerciante) throws NotFoundException{
		// TODO Auto-generated method stub
		Commerciante commerciante = commercianteRepository.findById(idCommerciante).orElseThrow(() -> new NotFoundException("COMMERCIANTE NON TROVATO"));
	Utente utente = commerciante.getUtente();
	if(utente == null) throw new NotFoundException("UTENTE NON TROVATO");
	utente.setStato(true);
		utenteRepository.save(utente);
	}
	
	
	public List<Utente> getCommerciantiDaAccettare() {
		// TODO Auto-generated method stub
		List<Utente> commercianti =utenteRepository.findAll();
		List<Utente> commerciantiDaAccettare = new ArrayList<>();
		commercianti.forEach(Utente ->{
			if(Utente.getNomeRuolo()==NomiRuoli.ROLE_COMMERCIANTE && !Utente.isStato()) commerciantiDaAccettare.add(Utente);
		});
		if (commerciantiDaAccettare.isEmpty()) return null;
		
		
		return commerciantiDaAccettare;
	}
	
	public Commerciante getCommercianteByIdUtente(Long idUtente) throws IllegalArgumentException,NotFoundException{
		// TODO Auto-generated method stub
		Utente utente = utenteRepository.findById(idUtente).orElseThrow(() -> new NotFoundException("Utente NON TROVATO"));
		if(utente.getNomeRuolo()!= NomiRuoli.ROLE_COMMERCIANTE) throw new IllegalArgumentException("UTENTE NON E UN CORRIERE");

		Commerciante commerciante = (Commerciante) utente.getAssociato();
		if(commerciante ==null) throw new NotFoundException("COMMERCIANTE NON TROVATO");
		return commerciante;
	}


	public List<Commerciante> getCommercianti() {
		// TODO Auto-generated method stub
		Iterable<Commerciante> iteratore =commercianteRepository.findAll();
		List<Commerciante> commercianti = new ArrayList<>();
		iteratore.forEach(Commerciante ->{
			 commercianti.add(Commerciante);
		});
		if(commercianti.isEmpty()) return null;
		return commercianti;
	}
	
	
	
	
}
