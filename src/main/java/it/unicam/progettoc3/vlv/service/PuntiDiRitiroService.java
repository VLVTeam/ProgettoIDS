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
public class PuntiDiRitiroService  {

	@Autowired
	PuntoDiRitiroRepository puntoDiRitiroRepository;
	
	public void addPuntoDiRitiro(PuntoDiRitiro puntoDiRitiro) {
		// TODO Auto-generated method stub
		
			puntoDiRitiroRepository.save(puntoDiRitiro);
			 
		
	}

	
	public void deletePuntoDiRitiro(Long idPuntoDiRitiro) throws NotFoundException{
		// TODO Auto-generated method stub
		
		PuntoDiRitiro puntoDiRitiro = puntoDiRitiroRepository.findById(idPuntoDiRitiro).orElseThrow(()->new  NotFoundException("Punto di ritiro non trovato"));
		
		
		
			//puntoDiRitiroRepository.delete(puntoDiRitiro);
			
			puntoDiRitiroRepository.removePuntoDiRitiroById(puntoDiRitiro.getID());
		
		
	}

	
	public List<PuntoDiRitiro> getPuntiDiRitiro() {
		// TODO Auto-generated method stub
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
