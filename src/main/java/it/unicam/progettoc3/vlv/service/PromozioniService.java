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
/**
 * La classe PromozioniService definisce precisamente il funzionamento
 * dei metodi presenti anche nella classe controller, fornendoli appunto alla classe PromozioniController.
 */
public class PromozioniService {

	// collegamenti alle repository
	@Autowired
	PromozioneRepository promozioneRepository;
	
	@Autowired
	CommercianteRepository commercianteRepository;
	
	@Autowired
	UtenteRepository utenteRepository;
	
	public List<Promozione> getPromozioni() {
		// TODO Auto-generated method stub
		
		// crea una lista di promozioni, la riempie con tutte le promozioni presenti e la restituisce, puo' essere null
		List<Promozione> promozioni= new ArrayList<>();
		Iterable<Promozione> iteratore = promozioneRepository.findAll();

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
	
		/*
		 * se la promozione passata al metodo ha: data di inizio uguale a null, oppure data di fine uguale a
		 * null, oppure la data di fine arrivera' prima della data di inizio, stampa errore
		 */
		if(promozione.getDataInizio() == null || promozione.getDataFine()==null || promozione.getDataFine().before(promozione.getDataInizio()))
		{
			throw new IllegalArgumentException("CONTROLLA DATE");
		}
	
		// altrimenti salva la promozione
		Promozione promozioneSave = new Promozione(promozione.getDescrizione(), commerciante, promozione.getDataInizio(), promozione.getDataFine());
		promozioneRepository.save(promozioneSave);

	}

	
	
	public List<Promozione> getPromozioniFiltrate(CategorieMerceologiche categoriaMerceologica) throws NotFoundException{
		// TODO Auto-generated method stub
		// crea una lista di promozioni che riempie con promozioni non scadute e un'altra vuota
		List<Promozione> promozioni= getPromozioniNonScadute();
		List<Promozione> promozioniReturn= new ArrayList<>();
		// se non esistono promozioni non scadute, restituisce errore
		if (promozioni.isEmpty()){ return null;}
		/*
		 * altrimenti riempie la seconda lista con le promozioni presenti nella prima che pero' hanno l'attributo
		 * commerciante il quale ha la categoria merceologica equivalente a quella passata al metodo
		 */
		promozioni.forEach(Promozione ->
		{
			Commerciante commerciante = commercianteRepository.findById(Promozione.getIdCommerciante()).get();
			if(commerciante.getCategoriaMerceologica()==categoriaMerceologica){promozioniReturn.add(Promozione);}
		});
		// restituisce la lista risultante, puo' anche essere null
		if(promozioniReturn.isEmpty()){return null;}
		else
		{
			return promozioniReturn;
		}
	}

	
	
	public List<Promozione> getPromozioniCommerciante(String emailCommerciante) throws NotFoundException{
		// TODO Auto-generated method stub
		
		// controlla validita' commerciante
		Utente utente = utenteRepository.findByEmail(emailCommerciante).orElseThrow(() -> new NotFoundException("COMMERCIANTE NON TROVATO"));
		Commerciante commerciante = (Commerciante) utente.getAssociato();
		
		// riempie la lista promozioni con tutte le promozioni legate al commerciante trovato
		List<Promozione> promozioni= commerciante.getPromozioni();

		// restituisce la lista risultante, puo' anche essere null
		if(promozioni.isEmpty()){return null;}
		else{
			return promozioni;
			}
	}

	
	public List<Promozione> getPromozioniNonScadute() {
		// TODO Auto-generated method stub
		// crea due liste di promozioni, la prima la riempie con tutte le promozioni esistenti
		List<Promozione> promozioni= getPromozioni();
		Date dataAttuale = new Date();
		List<Promozione> promozioniNonScadute= new ArrayList<>();
		/*
		 * scorre tutte le promozioni della prima lista, se la data di fine di una promozione va oltre alla
		 * data in cui viene invocato queso metodo, allora la promozione non e' scaduta, quindi viene inserita nella
		 * seconda lista di promozioni, alla fine la seconda lista conterra' tutte le promozioni non ancora scadute
		 */
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


	public void deletePromozione(Long idPromozione) throws NotFoundException{
		// TODO Auto-generated method stub
		// cerca la promozione tramite id passato, se la trova la elimina dalla repository
		Promozione promozione = promozioneRepository.findById(idPromozione).orElseThrow(() -> new NotFoundException("PROMOZIONE NON TROVATA"));
		
		promozioneRepository.removePromozioneById(promozione.getID());
	}

}