package it.unicam.progettoc3.vlv.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.unicam.progettoc3.vlv.controller.IPuntiDiRitiro;
import it.unicam.progettoc3.vlv.entity.elementi.PuntoDiRitiro;
import it.unicam.progettoc3.vlv.repository.PuntoDiRitiroRepository;

@Service
public class PuntiDiRitiroService implements IPuntiDiRitiro {

	@Autowired
	PuntoDiRitiroRepository puntoDiRitiroRepository;
	@Override
	public ResponseEntity<String> addPuntoDiRitiro(PuntoDiRitiro puntoDiRitiro) {
		// TODO Auto-generated method stub
		
			puntoDiRitiroRepository.save(puntoDiRitiro);
			return new ResponseEntity<String>("PUNTO DI RITIRO  AGGIUNTO" , HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<String> removePuntoDiRitiro(Long idPuntoDiRitiro) {
		// TODO Auto-generated method stub
		Optional<PuntoDiRitiro> optionalPuntoDiRitiro= puntoDiRitiroRepository.findById(idPuntoDiRitiro);
		
		
		if(!optionalPuntoDiRitiro.isPresent())
		{
			return new ResponseEntity<String>("PUNTO DI RITIRO NON TROVATO , Impossibile rimuovere" , HttpStatus.NOT_ACCEPTABLE);
		}
		else 
		{
			puntoDiRitiroRepository.deleteById(idPuntoDiRitiro);
			return new ResponseEntity<String>("PUNTO DI RITIRO RIMOSSO" , HttpStatus.OK);
		}
		
	}

	@Override
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
