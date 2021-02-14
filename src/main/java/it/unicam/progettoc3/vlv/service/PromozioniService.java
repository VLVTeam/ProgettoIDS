package it.unicam.progettoc3.vlv.service;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unicam.progettoc3.vlv.entity.dto.NuovaPromozione;
import it.unicam.progettoc3.vlv.entity.elementi.Promozione;
import it.unicam.progettoc3.vlv.entity.enumeratori.CategorieMerceologiche;
import it.unicam.progettoc3.vlv.entity.utenti.Commerciante;
import it.unicam.progettoc3.vlv.entity.utenti.Utente;
import it.unicam.progettoc3.vlv.repository.CommercianteRepository;
import it.unicam.progettoc3.vlv.repository.PromozioneRepository;
import it.unicam.progettoc3.vlv.repository.UtenteRepository;
import javassist.NotFoundException;

@Service
@Transactional
public class PromozioniService {

	@Autowired
	PromozioneRepository promozioneRepository;
	
	@Autowired
	CommercianteRepository commercianteRepository;
	@Autowired
	UtenteRepository utenteRepository;
	
	public List<Promozione> getPromozioni() {
		// TODO Auto-generated method stub
		
		List<Promozione> promozioni= new ArrayList<>();
		Iterable<Promozione> iteratore = promozioneRepository.findAll();
		;
		

		iteratore.forEach(Promozione -> promozioni.add(Promozione));
		
		
		
		
		if (promozioni.isEmpty()){return null;}
		else
		{
			return promozioni;
			}
	}

	
	public void addPromozione(NuovaPromozione promozione , String emailCommerciante) throws IllegalArgumentException , NotFoundException {
		// TODO Auto-generated method stub
		
		//controllo validita idCommerciante
		Utente utente = utenteRepository.findByEmail(emailCommerciante).orElseThrow(()->new  NotFoundException("commerciante non trovato"));
		Commerciante commerciante = (Commerciante) utente.getAssociato();
	
	if(promozione.getDataInizio() == null || promozione.getDataFine()==null || promozione.getDataFine().before(promozione.getDataInizio()))
	{
		
		throw new IllegalArgumentException("CONTROLLA DATE");
	}
	
		Promozione promozioneSave = new Promozione(promozione.getDescrizione(), commerciante, promozione.getDataInizio(), promozione.getDataFine());
		
	
		promozioneRepository.save(promozioneSave);
		
	
	
	

	}

	
		
		
	

	
	public List<Promozione> getPromozioniFiltrate(CategorieMerceologiche categoriaMerceologica) throws NotFoundException{
		// TODO Auto-generated method stub
		List<Promozione> promozioni= getPromozioniNonScadute();
		List<Promozione> promozioniReturn= new ArrayList<>();
		if (promozioni.isEmpty()){ return null;}
		promozioni.forEach(Promozione ->
		{
			
			Commerciante commerciante = commercianteRepository.findById(Promozione.getIdCommerciante()).get();
			if(commerciante.getCategoriaMerceologica()==categoriaMerceologica){promozioniReturn.add(Promozione);}
		});
		if(promozioniReturn.isEmpty()){return null;}
		else{
			return promozioniReturn;
			}
	}

	
	public List<Promozione> getPromozioniCommerciante(String emailCommerciante) throws NotFoundException{
		// TODO Auto-generated method stub
		
		Utente utente = utenteRepository.findByEmail(emailCommerciante).orElseThrow(() -> new NotFoundException("COMMERCIANTE NON TROVATO"));
		Commerciante commerciante = (Commerciante) utente.getAssociato();
		
		List<Promozione> promozioni= commerciante.getPromozioni();
		
		
		if(promozioni.isEmpty()){return null;}
		else{
			return promozioni;
			}
	}

	
	public List<Promozione> getPromozioniNonScadute() {
		// TODO Auto-generated method stub
		List<Promozione> promozioni= getPromozioni();
		Date dataAttuale = new Date();
		List<Promozione> promozioniNonScadute= new ArrayList<>();
		promozioni.forEach(Promozione ->
		{
			if(!Promozione.getDataFine().before(dataAttuale)){
				promozioniNonScadute.add(Promozione);
			}
		});
		
		if (promozioniNonScadute.isEmpty()){return null;}
		else
		{
			return promozioniNonScadute;
			}
	}


	/*
	public void modificaPromozione(Long idPromozione, String descrizione, Date dataInizio,Date dataFine) throws IllegalArgumentException,NotFoundException{
		// TODO Auto-generated method stub
		Promozione promozione = promozioneRepository.findById(idPromozione).orElseThrow(() -> new NotFoundException("PROMOZIONE NON TROVATA"));
		
		if((descrizione==null || descrizione.isEmpty())&& dataInizio==null && dataFine ==null){throw new IllegalArgumentException("TUTTI I CAMPI SONO VUOTI");}
	
	
	if(descrizione!=null && !descrizione.isEmpty()){promozione.setDescrizione(descrizione);}
	if(dataInizio!=null){promozione.setDataInizio(dataInizio);}
	if(dataFine!=null){promozione.setDataFine(dataFine);}
	promozioneRepository.save(promozione);
	
	}

*/
	public void deletePromozione(Long idPromozione) throws NotFoundException{
		// TODO Auto-generated method stub
		Promozione promozione = promozioneRepository.findById(idPromozione).orElseThrow(() -> new NotFoundException("PROMOZIONE NON TROVATA"));
		
		promozioneRepository.delete(promozione);
	}

}
