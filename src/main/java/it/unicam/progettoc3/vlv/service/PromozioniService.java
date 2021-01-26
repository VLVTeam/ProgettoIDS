package it.unicam.progettoc3.vlv.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.unicam.progettoc3.vlv.controller.IPromozioni;
import it.unicam.progettoc3.vlv.entity.elementi.Promozione;
import it.unicam.progettoc3.vlv.entity.elementi.PromozioneRepository;
import it.unicam.progettoc3.vlv.entity.enumeratori.CategorieMerceologiche;

import it.unicam.progettoc3.vlv.entity.utenti.Commerciante;
import it.unicam.progettoc3.vlv.entity.utenti.CommercianteRepository;

@Service
public class PromozioniService implements IPromozioni {

	@Autowired
	PromozioneRepository promozioneRepository;
	
	@Autowired
	CommercianteRepository commercianteRepository;
	
	@Override
	public List<Promozione> getPromozioni() {
		// TODO Auto-generated method stub
		
		List<Promozione> promozioni= new ArrayList<>();
		Iterable<Promozione> iteratore = promozioneRepository.findAll();
		
		iteratore.forEach(Ordine -> promozioni.add(Ordine));
		if (promozioni.isEmpty()){return null;}
		else
		{
			return promozioni;
			}
	}

	@Override
	public ResponseEntity<String> addPromozione(Promozione promozione) {
		// TODO Auto-generated method stub
		
		//controllo validita idCommerciante
		Optional<Commerciante> optionalCommerciante = commercianteRepository.findById(promozione.getCommerciante().getID());
		if(!optionalCommerciante.isPresent()) {  return new ResponseEntity<String>("Commerciante non trovato" , HttpStatus.BAD_REQUEST);}
	
	
	
	
	try {
		promozioneRepository.save(promozione);
		return new ResponseEntity<String>("Promozione AGGIUNTA" , HttpStatus.OK);
	} catch (IllegalArgumentException e) {
		// TODO: handle exception
		return new ResponseEntity<String>("CONTROLLARE CAMPI Promozione" , HttpStatus.NOT_ACCEPTABLE);
	}
	
	

	}

	@Override
	public ResponseEntity<String> removePromozione(Promozione promozione) {
		// TODO Auto-generated method stub
		
		List<Promozione> promozioni= new ArrayList<>();
		promozioni = getPromozioni();
		if(promozioni.isEmpty())
		{
			return new ResponseEntity<String>("Promozione Non Trovata , Impossibile rimuovere" , HttpStatus.NOT_ACCEPTABLE);
		}
		else if(promozioni.contains(promozione))
		{
			promozioni.remove(promozione.getID());
			return new ResponseEntity<String>("Promozione Rimossa" , HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Promozione Non Trovata , Impossibile rimuovere" , HttpStatus.NOT_ACCEPTABLE);
		}
		
	}

	@Override
	public List<Promozione> getPromozioniFiltrate(CategorieMerceologiche categoriaMerceologica) {
		// TODO Auto-generated method stub
		List<Promozione> promozioni= getPromozioni();
		if (promozioni.isEmpty()){ return null;}
		promozioni.forEach(Promozione ->
		{
			if(Promozione.getCommerciante().getCategoriaMerceologica()!=categoriaMerceologica){promozioni.remove(Promozione.getID());}
		});
		if(promozioni.isEmpty()){return null;}
		else{
			return promozioni;
			}
	}



}
