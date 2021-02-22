package it.unicam.progettoc3.vlv.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import it.unicam.progettoc3.vlv.entity.elementi.PuntoDiRitiro;
import it.unicam.progettoc3.vlv.repository.PuntoDiRitiroRepository;
import javassist.NotFoundException;

@Service
@Transactional
/**
 * La classe PuntiDiRitiroService definisce precisamente il funzionamento
 * dei metodi presenti anche nella classe controller, fornendoli appunto alla classe PuntiDiRitiroController.
 */
public class PuntiDiRitiroService  {

	@Autowired
	PuntoDiRitiroRepository puntoDiRitiroRepository;
	
	public void addPuntoDiRitiro(PuntoDiRitiro puntoDiRitiro) {
		// TODO Auto-generated method stub
		
			puntoDiRitiroRepository.save(puntoDiRitiro);
	}

	
	public void deletePuntoDiRitiro(Long idPuntoDiRitiro) throws NotFoundException{
		// TODO Auto-generated method stub
		// se trova il punto di ritiro attraverso l'id passato, lo elimina dalla repository
		PuntoDiRitiro puntoDiRitiro = puntoDiRitiroRepository.findById(idPuntoDiRitiro).orElseThrow(()->new  NotFoundException("Punto di ritiro non trovato"));
				
		puntoDiRitiroRepository.removePuntoDiRitiroById(puntoDiRitiro.getID());
		
	}

	
	public List<PuntoDiRitiro> getPuntiDiRitiro() {
		// TODO Auto-generated method stub
		// crea una lista di punti di ritiro, la riempie con tutti i punti di ritiro esistenti e la stampa, puo' anche essere null
		List<PuntoDiRitiro> puntiDiRitiro= new ArrayList<>();
		Iterable<PuntoDiRitiro> iteratore = puntoDiRitiroRepository.findAll();
		iteratore.forEach(PuntoDiRitiro -> puntiDiRitiro.add(PuntoDiRitiro));
		if(puntiDiRitiro.isEmpty()) {return null;}
		else
		{
			return puntiDiRitiro;
		}
	}

	
}