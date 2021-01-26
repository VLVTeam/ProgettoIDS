package it.unicam.progettoc3.vlv.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.unicam.progettoc3.vlv.controller.IPuntiDiRitiro;
import it.unicam.progettoc3.vlv.entity.elementi.PuntoDiRitiro;
import it.unicam.progettoc3.vlv.entity.elementi.PuntoDiRitiroRepository;

@Service
public class PuntiDiRitiroService implements IPuntiDiRitiro {

	@Autowired
	PuntoDiRitiroRepository puntoDiRitiroRepository;
	@Override
	public ResponseEntity<String> addPuntoDiRitiro(PuntoDiRitiro puntoDiRitiro) {
		// TODO Auto-generated method stub
		try {
			puntoDiRitiroRepository.save(puntoDiRitiro);
			return new ResponseEntity<String>("PUNTO DI RITIRO  AGGIUNTO" , HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			return new ResponseEntity<String>("CONTROLLARE CAMPI PUNTO DI RITIRO" , HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> removePuntoDiRitiro(PuntoDiRitiro puntoDiRitiro) {
		// TODO Auto-generated method stub
		List<PuntoDiRitiro> puntiDiRitiro= new ArrayList<>();
		puntiDiRitiro = getPuntiDiRitiro();
		if(puntiDiRitiro.isEmpty())
		{
			return new ResponseEntity<String>("PUNTO DI RITIRO NON TROVATO , Impossibile rimuovere" , HttpStatus.NOT_ACCEPTABLE);
		}
		else if(puntiDiRitiro.contains(puntoDiRitiro))
		{
			puntiDiRitiro.remove(puntoDiRitiro.getID());
			return new ResponseEntity<String>("PUNTO DI RITIRO RIMOSSO" , HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("PUNTO DI RITIRO NON TROVATO , Impossibile rimuovere" , HttpStatus.NOT_ACCEPTABLE);
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
