package it.unicam.progettoc3.vlv.service;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.unicam.progettoc3.vlv.controller.IPromozioni;
import it.unicam.progettoc3.vlv.entity.elementi.Promozione;
import it.unicam.progettoc3.vlv.entity.enumeratori.CategorieMerceologiche;
import it.unicam.progettoc3.vlv.entity.riferimenti.PromozioneRiferimento;
import it.unicam.progettoc3.vlv.entity.utenti.Commerciante;
import it.unicam.progettoc3.vlv.repository.CommercianteRepository;
import it.unicam.progettoc3.vlv.repository.PromozioneRepository;

@Service
public class PromozioniService implements IPromozioni {

	@Autowired
	PromozioneRepository promozioneRepository;
	
	@Autowired
	CommercianteRepository commercianteRepository;
	
	@Override
	public List<PromozioneRiferimento> getPromozioni() {
		// TODO Auto-generated method stub
		
		List<Promozione> promozioni= new ArrayList<>();
		Iterable<Promozione> iteratore = promozioneRepository.findAll();
		;
		

		iteratore.forEach(Promozione -> promozioni.add(Promozione));
		if(promozioni.isEmpty()){return null;}
		
		List<PromozioneRiferimento> promozioniRiferimento= new ArrayList<>();
		
		promozioni.forEach(Promozione ->
		{
			promozioniRiferimento.add(new PromozioneRiferimento(Promozione.getID(), Promozione.getDescrizione(), Promozione.getCommerciante().getID(), Promozione.getDataInizio(), Promozione.getDataFine()));
			
		});
		
		if (promozioniRiferimento.isEmpty()){return null;}
		else
		{
			return promozioniRiferimento;
			}
	}

	@Override
	public ResponseEntity<String> addPromozione(PromozioneRiferimento promozione) {
		// TODO Auto-generated method stub
		
		//controllo validita idCommerciante
		Optional<Commerciante> optionalCommerciante = commercianteRepository.findById(promozione.getIdCommerciante());
		if(!optionalCommerciante.isPresent()) {  return new ResponseEntity<String>("Commerciante non trovato" , HttpStatus.BAD_REQUEST);}
	
	if(promozione.getDataInizio() == null || promozione.getDataFine()==null || promozione.getDataFine().before(promozione.getDataInizio()))
	{
		return new ResponseEntity<String>("CONTROLLA DATE" , HttpStatus.NOT_ACCEPTABLE);
	}
		Commerciante commerciante = optionalCommerciante.get();
		Promozione promozioneSave = new Promozione(promozione.getDescrizione(), commerciante, promozione.getDataInizio(), promozione.getDataFine());
		
		
	
	
		promozioneRepository.save(promozioneSave);
		return new ResponseEntity<String>("PROMOZIONE AGGIUNTA" , HttpStatus.OK);
	
	
	

	}

	
		
		
	

	@Override
	public List<PromozioneRiferimento> getPromozioniFiltrate(CategorieMerceologiche categoriaMerceologica) {
		// TODO Auto-generated method stub
		List<PromozioneRiferimento> promozioniRiferimento= getPromozioniNonScadute();
		List<PromozioneRiferimento> promozioniRiferimentoReturn= new ArrayList<>();
		if (promozioniRiferimento.isEmpty()){ return null;}
		promozioniRiferimento.forEach(PromozioneRiferimento ->
		{
			Commerciante commerciante = commercianteRepository.findById(PromozioneRiferimento.getIdCommerciante()).get();
			if(commerciante.getCategoriaMerceologica()==categoriaMerceologica){promozioniRiferimentoReturn.add(PromozioneRiferimento);}
		});
		if(promozioniRiferimentoReturn.isEmpty()){return null;}
		else{
			return promozioniRiferimentoReturn;
			}
	}

	@Override
	public List<PromozioneRiferimento> getPromozioniCommerciante(Long idCommerciante) {
		// TODO Auto-generated method stub
		Optional<Commerciante> optionalCommerciante = commercianteRepository.findById(idCommerciante);
		if(!optionalCommerciante.isPresent()){return null;}
		List<PromozioneRiferimento> promozioniRiferimento= getPromozioni();
		List<PromozioneRiferimento> promozioniRiferimentoReturn= getPromozioni();
		if (promozioniRiferimento.isEmpty()){ return null;}
		promozioniRiferimento.forEach(PromozioneRiferimento ->
		{
			
			if(PromozioneRiferimento.getIdCommerciante()==idCommerciante){promozioniRiferimentoReturn.remove(PromozioneRiferimento.getId());}
		});
		if(promozioniRiferimentoReturn.isEmpty()){return null;}
		else{
			return promozioniRiferimentoReturn;
			}
	}

	@Override
	public List<PromozioneRiferimento> getPromozioniNonScadute() {
		// TODO Auto-generated method stub
		List<PromozioneRiferimento> promozioniRiferimento= getPromozioni();
		Date dataAttuale = new Date();
		List<PromozioneRiferimento> promozioniRiferimentoNonScadute= new ArrayList<>();
		promozioniRiferimento.forEach(PromozioneRiferimento ->
		{
			if(!PromozioneRiferimento.getDataFine().before(dataAttuale)){
				promozioniRiferimentoNonScadute.add(PromozioneRiferimento);
			}
		});
		
		if (promozioniRiferimento.isEmpty()){return null;}
		else
		{
			return promozioniRiferimentoNonScadute;
			}
	}


	@Override
	public ResponseEntity<String> modificaPromozione(Long idPromozione, String descrizione, Date dataInizio,Date dataFine) {
		// TODO Auto-generated method stub
		Optional<Promozione> optionalPromozione = promozioneRepository.findById(idPromozione);
		if(!optionalPromozione.isPresent()){return new ResponseEntity<String>("PROMOZIONE NON TROVATA , CONTROLLA ID" , HttpStatus.BAD_REQUEST);}
		Promozione promozione = optionalPromozione.get();
		if((descrizione==null || descrizione.isEmpty())&& dataInizio==null && dataFine ==null){return new ResponseEntity<String>("TUTTI I CAMPI DI MODIFICA SONO VUOTI" , HttpStatus.NOT_ACCEPTABLE);}
	
	
	if(descrizione!=null && !descrizione.isEmpty()){promozione.setDescrizione(descrizione);}
	if(dataInizio!=null){promozione.setDataInizio(dataInizio);}
	if(dataFine!=null){promozione.setDataFine(dataFine);}
	promozioneRepository.save(promozione);
	return new ResponseEntity<String>("PROMOZIONE MODIFICATA" , HttpStatus.OK);
	}

}
