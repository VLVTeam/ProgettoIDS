package it.unicam.progettoc3.vlv.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unicam.progettoc3.vlv.entity.enumeratori.NomiRuoli;
import it.unicam.progettoc3.vlv.entity.utenti.Corriere;
import it.unicam.progettoc3.vlv.entity.utenti.Utente;
import it.unicam.progettoc3.vlv.repository.CorriereRepository;
import it.unicam.progettoc3.vlv.repository.UtenteRepository;
import javassist.NotFoundException;



@Service
@Transactional
public class CorriereService {

	
	@Autowired
	CorriereRepository corriereRepository;
	@Autowired
	UtenteRepository utenteRepository;
	
	public void save(Corriere corriere)
	{
		corriereRepository.save(corriere);
	}
	
	
	
	
	
	
	public void accettaIscrizioneCorriere(Long idCorriere) throws NotFoundException{
		// TODO Auto-generated method stub
		Corriere corriere = corriereRepository.findById(idCorriere).orElseThrow(() -> new NotFoundException("CORRIERE NON TROVATO"));
		Utente utente = corriere.getUtente();
		if(utente == null) throw new NotFoundException("UTENTE NON TROVATO");
		utente.setStato(true);
			utenteRepository.save(utente);
	}

	
	

	public List<Utente> getCorrieriDaAccettare() {
		// TODO Auto-generated method stub
		Iterable<Utente> iteratore =utenteRepository.findAll();
		List<Utente> corrieriDaAccettare = new ArrayList<>();
		iteratore.forEach(Utente ->{
			if(Utente.getNomeRuolo()==NomiRuoli.ROLE_CORRIERE && !Utente.isStato()) corrieriDaAccettare.add(Utente);
		});
		if (corrieriDaAccettare.isEmpty()) return null;
		return corrieriDaAccettare;
	}

	

	public Corriere getCorriereByIdUtente(Long idUtente) throws  IllegalArgumentException,NotFoundException{
		// TODO Auto-generated method stub
		Utente utente = utenteRepository.findById(idUtente).orElseThrow(() -> new NotFoundException("Utente NON TROVATO"));
		if(utente.getNomeRuolo()!= NomiRuoli.ROLE_CORRIERE) throw new IllegalArgumentException("UTENTE NON E UN CORRIERE");
		Corriere corriere = (Corriere) utente.getAssociato();
		if(corriere ==null) throw new NotFoundException("CORRIERE NON TROVATO");
		return corriere;
	}
}
